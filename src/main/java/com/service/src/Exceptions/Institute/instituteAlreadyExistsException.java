package com.service.src.Exceptions.Institute;

public class instituteAlreadyExistsException extends RuntimeException {
    public instituteAlreadyExistsException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}
