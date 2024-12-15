package com.database.medicine.service;

import com.database.medicine.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SrvcService {

    private final ServiceRepository serviceRepository;

    public List<com.database.medicine.entity.Service> findAll() {
        return (List<com.database.medicine.entity.Service>) serviceRepository.findAll();
    }

    public Optional<com.database.medicine.entity.Service> findById(int id) {
        return serviceRepository.findById(id);
    }
}
