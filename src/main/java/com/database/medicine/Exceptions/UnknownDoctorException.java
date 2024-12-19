package com.database.medicine.Exceptions;

public class UnknownDoctorException extends RuntimeException {
    public UnknownDoctorException(String message) {
        super(message);
    }

    public UnknownDoctorException() {
        this("Выбранный Вами доктор не найден");
    }
}
