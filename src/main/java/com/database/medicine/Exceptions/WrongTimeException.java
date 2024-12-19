package com.database.medicine.Exceptions;

public class WrongTimeException extends RuntimeException {

    public WrongTimeException(String message) {
        super(message);
    }

    public WrongTimeException() {
        this("Нельзя записаться на выбранное Вами время");
    }
}
