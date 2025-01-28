package com.database.medicine.repository;

import com.database.medicine.entity.Logs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends CrudRepository<Logs, Integer> {

    Page<Logs> findAll(Pageable pageable);
}
