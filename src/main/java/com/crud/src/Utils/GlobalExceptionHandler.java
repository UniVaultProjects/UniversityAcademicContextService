package com.crud.src.Utils;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UniversityNotFoundException.class)
    public ResponseEntity<Object> handleUniversityNotFoundException(UniversityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // 404 Not Found
                .body(exception.getMessage()); // The exception message is returned as the response body
    }

    @ExceptionHandler(UniversityAlreadyExistsException.class)
    public ResponseEntity<Object> handleUniversityAlreadyExistsException(
            UniversityAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400 Bad Request
                .body(exception.getMessage());
    }
}
