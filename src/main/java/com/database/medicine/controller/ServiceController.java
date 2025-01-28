package com.database.medicine.controller;

import com.database.medicine.entity.Service;
import com.database.medicine.service.SrvcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
