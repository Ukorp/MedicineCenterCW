package com.database.medicine.Exceptions;

public class UnknownServiceException extends RuntimeException {
    public UnknownServiceException(String message) {
        super(message);
    }

    public UnknownServiceException() {
        this("Выбранная Вами услуга не найдена");
    }
}
