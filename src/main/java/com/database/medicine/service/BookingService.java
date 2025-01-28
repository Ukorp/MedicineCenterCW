package com.database.medicine.service;

import com.database.medicine.Exceptions.*;
import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.dto.mail.MailResponse;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.Doctors;
import com.database.medicine.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.database.medicine.repository.BookingRepository;
import com.database.medicine.repository.ServiceDoctorsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class BookingService {
    private final BookingRepository bookingRepository;

    private final UserService userService;
    private final DoctorService doctorService;

    private final SrvcService srvcService;
    private final ServiceDoctorsRepository serviceDoctorsRepository;
    private final JwtService jwtService;
    private final KafkaSender kafkaSender;
    private final ObjectMapper objectMapper;

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Page<Booking> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }

    public void deleteAll() {
        bookingRepository.deleteAll();
    }

    public void update(Integer id, Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> findByDoctorId(Doctors doctor) {
        return bookingRepository.findByDoctorId(doctor);
    }

    public List<Booking> findByUserId(User user) {
        return bookingRepository.findByUserId(user);
    }

    public List<Booking> findBookingsByUserIdArchive() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingRepository.findBookingsByUserIdArchive(currentUser.getId());
    }

    public List<Booking> findBookingsByUserIdRelevant() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingRepository.findBookingsByUserIdRelevant(currentUser);
    }

    public Booking createBooking(BookingRequest bookingRequest) throws JsonProcessingException {
        Doctors doctor = doctorService.findById(bookingRequest.getDoctorId()).orElseThrow(UnknownDoctorException::new);
        List<Booking> DoctorBookings = bookingRepository
                .findByDoctorId(doctor);
        com.database.medicine.entity.Service service = srvcService
                .findById(bookingRequest.getServiceId())
                .orElseThrow(UnknownServiceException::new);
        serviceDoctorsRepository.getServicesDoctorsByDoctorIdAndServiceId(doctor, service)
                .orElseThrow(WrongDoctorException::new);
        if (DoctorBookings.stream()
                .anyMatch(booking -> booking.getDate().equals(bookingRequest.getDate()))) {
            throw new DoctorIsBusyException();
        }

        if (bookingRequest.getDate().isBefore(LocalDateTime.now())) {
            throw new WrongTimeException();
        }

        Booking currentBooking = Booking.builder()
                .serviceId(srvcService.findById(bookingRequest.getServiceId()).orElseThrow(UnknownServiceException::new))
                .userId(userService.findById(bookingRequest.getUserId()).orElse(null))
                .doctorId(doctorService.findById(bookingRequest.getDoctorId()).orElseThrow(UnknownDoctorException::new))
                .date(bookingRequest.getDate())
                .build();

        var userEmail = userService.findById(bookingRequest.getUserId()).orElseThrow();

//        var req = objectMapper.writeValueAsString(new MailResponse(currentBooking, userEmail.getEmail()));
        kafkaSender.send("mail", new MailResponse(currentBooking, userEmail.getEmail()));
        System.out.println(new MailResponse(currentBooking, userEmail.getEmail()).toString());
        return bookingRepository.save(currentBooking);
    }
}
