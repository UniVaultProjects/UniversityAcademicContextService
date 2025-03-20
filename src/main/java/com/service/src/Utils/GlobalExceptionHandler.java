package com.service.src.Utils;

import com.service.src.Utils.Institute.InstituteAlreadyExistsException;
import com.service.src.Utils.Institute.InstituteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InstituteNotFoundException.class)
    public ResponseEntity<Object> UniversityNotFoundException(InstituteNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // 404 Not Found
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
