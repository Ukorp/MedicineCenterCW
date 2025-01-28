package com.database.medicine.repository;

import com.database.medicine.entity.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {
    Optional<Service> getServiceById(Integer id);
}

