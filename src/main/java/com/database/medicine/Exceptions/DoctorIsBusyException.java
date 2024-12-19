package com.database.medicine.Exceptions;

public class DoctorIsBusyException extends RuntimeException {

    public DoctorIsBusyException(String message) {
        super(message);
    }

    public DoctorIsBusyException() {
        this("Доктор занят в выбранное Вами время");
    }
}
