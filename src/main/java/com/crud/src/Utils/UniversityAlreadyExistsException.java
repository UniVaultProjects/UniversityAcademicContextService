package com.crud.src.Utils;

public class UniversityAlreadyExistsException extends RuntimeException {
    public UniversityAlreadyExistsException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}
