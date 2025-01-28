package com.database.medicine.repository;

import com.database.medicine.entity.Doctors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctors, Integer> {

    Optional<Doctors> findDoctorsById(Integer id);
}
