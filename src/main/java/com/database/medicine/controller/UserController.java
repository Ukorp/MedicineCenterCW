package com.database.medicine.controller;

import com.database.medicine.dto.booking.BookingRequest;
import com.database.medicine.entity.Booking;
import com.database.medicine.entity.User;
import com.database.medicine.service.BookingService;
import com.database.medicine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<User> currentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

}
