package com.service.src.Utils.Institute;

public class InstituteAlreadyExistsException extends RuntimeException {
    public InstituteAlreadyExistsException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}
