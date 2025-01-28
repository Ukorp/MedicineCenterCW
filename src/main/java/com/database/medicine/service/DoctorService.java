package com.database.medicine.service;

import com.database.medicine.Exceptions.UnknownDoctorException;
import com.database.medicine.entity.Doctors;
import com.database.medicine.entity.ServicesDoctors;
import com.database.medicine.repository.DoctorRepository;
import com.database.medicine.repository.ServiceDoctorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {

    private final ServiceDoctorsRepository serviceDoctorsRepository;

    private final SrvcService srvcService;

    private final DoctorRepository doctorRepository;

    public List<Doctors> findByServiceId(int serviceId) {
        return serviceDoctorsRepository.getServicesDoctorsByServiceId(srvcService.findById(serviceId).orElseThrow(UnknownDoctorException::new))
                .stream()
                .map(ServicesDoctors::getDoctorId)
                .toList();
    }

    public Optional<Doctors> findById(int id) {
        return doctorRepository.findById(id);
    }
}
