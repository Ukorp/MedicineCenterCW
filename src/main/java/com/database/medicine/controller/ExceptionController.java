package com.database.medicine.controller;

import com.database.medicine.Exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({DoctorIsBusyException.class,
            UnknownDoctorException.class,
            UnknownServiceException.class,
            UnknownUserException.class,
            WrongDoctorException.class,
            WrongTimeException.class,
            AuthenticationException.class})
    public ResponseEntity<String> defaultHandler(RuntimeException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
}
