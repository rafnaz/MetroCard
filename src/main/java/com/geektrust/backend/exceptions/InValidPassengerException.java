package com.geektrust.backend.exceptions;

public class InValidPassengerException extends RuntimeException {

    public InValidPassengerException(String msg) {
        super(msg);
    }

    public InValidPassengerException() {
        super();
    }
}