package com.database.medicine.controller;

import com.database.medicine.Exceptions.DoctorIsBusyException;
import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.dto.booking.BookingResponse;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.User;
import com.database.medicine.service.BookingService;
import com.database.medicine.service.LogsService;
import com.database.medicine.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.database.medicine.entity.Logs;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin
public class AdminController {

    private final BookingService bookingService;
    private final UserService userService;
    private final LogsService logsService;

    @PostMapping("/booking/book")
    public ResponseEntity<Booking> book(@RequestBody BookingRequest bookingRequest) {
        if (bookingRequest.getUserId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
        } catch (DoctorIsBusyException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/booking/all")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(((List<Booking>) bookingService.findAll()).stream().map(BookingResponse::new).toList());
    }

    @GetMapping("/booking/page")
    public ResponseEntity<Page<BookingResponse>> getAllBookingsPagination(@RequestParam Integer offset, @RequestParam Integer limit) {
        return ResponseEntity.ok(bookingService.findAll(PageRequest.of(offset, limit)).map(BookingResponse::new));
    }

    @GetMapping("/logs/page")
    public ResponseEntity<Page<Logs>> getLogsPagination(@RequestParam Integer offset, @RequestParam Integer limit) {
        return ResponseEntity.ok(logsService.findAll(PageRequest.of(offset, limit, Sort.by("changeTime").descending())));
    }

    @GetMapping("user/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok((List<User>) userService.findAll());
    }

    @DeleteMapping("user/delete")
    public void deleteUser(@RequestParam Integer userId) {
        userService.deleteById(userId);
    }

    @PatchMapping("user/make_admin")
    public ResponseEntity<Boolean> changeToAdmin(@RequestParam Integer userId) {
        return ResponseEntity.ok(userService.makeAdmin(userId));
    }

    @PatchMapping("user/make_user")
    public ResponseEntity<Boolean> changeToUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(userService.makeUser(userId));
    }

    @PutMapping("user/update")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }
}
