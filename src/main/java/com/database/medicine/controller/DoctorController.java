package com.database.medicine.controller;


import com.database.medicine.entity.Booking;
import com.database.medicine.entity.Doctors;
import com.database.medicine.repository.BookingRepository;
import com.database.medicine.repository.DoctorRepository;
import com.database.medicine.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    private final BookingRepository bookingRepository;

    private final DoctorService doctorService;

    @GetMapping("/bookings")
    public ResponseEntity<List<LocalDateTime>> getBusyDays(@RequestParam Integer doctorId) {
        return ResponseEntity.ok(bookingRepository.findByDoctorId(doctorRepository.findById(doctorId).orElseThrow())
                .stream()
                .map(Booking::getDate).toList());
    }

    @GetMapping("/byService")
    public ResponseEntity<List<Doctors>> getDoctorByServiceId(@RequestParam Integer serviceId) {
        return ResponseEntity.ok(doctorService.findByServiceId(serviceId));
    }

}
