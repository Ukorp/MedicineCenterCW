package com.database.medicine.repository;

import com.database.medicine.entity.Doctors;
import com.database.medicine.entity.Service;
import com.database.medicine.entity.ServicesDoctors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceDoctorsRepository extends CrudRepository<ServicesDoctors, Integer> {

    Optional<ServicesDoctors> getServicesDoctorsByDoctorIdAndServiceId(Doctors doctorId, Service serviceId);

    List<ServicesDoctors> getServicesDoctorsByServiceId(Service byId);
}
