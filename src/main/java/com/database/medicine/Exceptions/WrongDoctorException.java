package com.database.medicine.Exceptions;

public class WrongDoctorException extends RuntimeException {
    public WrongDoctorException(String message) {
        super(message);
    }

    public WrongDoctorException() {
        this("Доктор не специализируется на выбранной Вами услуге");
    }
}
