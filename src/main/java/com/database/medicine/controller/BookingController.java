package com.database.medicine.controller;

import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.dto.booking.BookingResponse;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.User;
import com.database.medicine.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> book(@RequestBody BookingRequest bookingRequest) throws JsonProcessingException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookingRequest.setUserId(currentUser.getId());
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

    @GetMapping("/archive")
    public ResponseEntity<List<BookingResponse>> bookingArchive() {
        return ResponseEntity.ok(bookingService.findBookingsByUserIdArchive().stream().map(BookingResponse::new).toList());
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingResponse>> bookingUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(bookingService.findByUserId(currentUser).stream().map(BookingResponse::new).toList());
    }

    @GetMapping("/relevant")
    public ResponseEntity<List<BookingResponse>> bookingRelevant() {
        return ResponseEntity.ok(bookingService.findBookingsByUserIdRelevant().stream().map(BookingResponse::new).toList());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        bookingService.deleteById(id);
    }

}
