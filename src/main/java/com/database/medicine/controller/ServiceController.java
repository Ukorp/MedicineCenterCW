package com.database.medicine.controller;

import com.database.medicine.Exceptions.DoctorIsBusyException;
import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.dto.booking.BookingResponse;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.Service;
import com.database.medicine.entity.User;
import com.database.medicine.repository.ServiceRepository;
import com.database.medicine.service.BookingService;
import com.database.medicine.service.SrvcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/service")
@RequiredArgsConstructor
public class ServiceController {

    private final SrvcService srvcService;

    @GetMapping("/all")
    public ResponseEntity<List<Service>> bookingArchive() {
        return ResponseEntity.ok(srvcService.findAll());
    }

}
