package com.service.src.Exceptions;

import com.service.src.Exceptions.Institute.instituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.instituteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(instituteNotFoundException.class)
    public ResponseEntity<Object> UniversityNotFoundException(instituteNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // 404 Not Found
                .body(exception.getMessage()); // The exception message is returned as the response body
    }

    @ExceptionHandler(instituteAlreadyExistsException.class)
    public ResponseEntity<Object> UniversityAlreadyExistsException(
            instituteAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400 Bad Request
                .body(exception.getMessage());
    }
}
