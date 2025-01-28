package com.database.medicine.service;

import com.database.medicine.entity.Logs;
import com.database.medicine.repository.LogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogsService {
    private final LogsRepository logsRepository;

    public Page<Logs> findAll(Pageable pageable) {
        return logsRepository.findAll(pageable);
    }
}
