package com.enigma.controller;

import com.enigma.exception.NotFoundException;
import com.enigma.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NotFoundException exception){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
            ErrorResponse("X01", exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
                ErrorResponse("X06", exception.getMessage()));
    }
}
