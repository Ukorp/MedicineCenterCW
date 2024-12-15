package com.database.medicine.Exceptions;

public class DoctorIsBusyException extends RuntimeException {

    public DoctorIsBusyException(String message) {
        super(message);
    }
}
