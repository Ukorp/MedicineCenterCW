package com.database.medicine.service;

import com.database.medicine.Exceptions.DoctorIsBusyException;
import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.Doctors;
import com.database.medicine.entity.ServicesDoctors;
import com.database.medicine.entity.User;
import com.database.medicine.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Integer countAll() {
        return bookingRepository.countAll();
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
        return bookingRepository.findBookingsByUserIdArchive(currentUser);
    }

    public List<Booking> findBookingsByUserIdRelevant() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingRepository.findBookingsByUserIdRelevant(currentUser);
    }

    public Booking createBooking(BookingRequest bookingRequest) throws DoctorIsBusyException {
        List<Booking> DoctorBookings = bookingRepository
                .findByDoctorId(doctorService.findById(bookingRequest.getDoctorId()).orElseThrow());
        Doctors doctor = DoctorBookings.getFirst().getDoctorId();
        com.database.medicine.entity.Service service = srvcService.findById(bookingRequest.getServiceId()).orElseThrow();
        serviceDoctorsRepository.getServicesDoctorsByDoctorIdAndServiceId(doctor, service)
                .orElseThrow((() -> new DoctorIsBusyException("Доктор не имеет выбранной специальности")));
        if (DoctorBookings.stream()
                .anyMatch(booking -> booking.getDate().equals(bookingRequest.getDate()))) {
            throw new DoctorIsBusyException("Доктор занят в выбранное Вами время");
        }

        Booking currentBooking = Booking.builder()
                .serviceId(srvcService.findById(bookingRequest.getServiceId()).orElseThrow())
                .userId(userService.findById(bookingRequest.getUserId()).orElse(null))
                .doctorId(doctorService.findById(bookingRequest.getDoctorId()).orElseThrow())
                .date(bookingRequest.getDate())
                .build();

        return bookingRepository.save(currentBooking);
    }

    public Booking createBookingUser(BookingRequest bookingRequest) throws DoctorIsBusyException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookingRequest.setUserId(currentUser.getId());
        return createBooking(bookingRequest);
    }
}
