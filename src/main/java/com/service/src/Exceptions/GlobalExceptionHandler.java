package com.service.src.Exceptions;

import com.service.src.Exceptions.Institute.InstituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.InstituteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InstituteNotFoundException.class)
    public ResponseEntity<Object> UniversityNotFoundException(InstituteNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // 404 Not Found
                .body(exception.getMessage()); // The exception message is returned as the response body
    }

    @ExceptionHandler(InstituteAlreadyExistsException.class)
    public ResponseEntity<Object> UniversityAlreadyExistsException(
            InstituteAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400 Bad Request
                .body(exception.getMessage());
    }
}
