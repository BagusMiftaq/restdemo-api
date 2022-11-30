package com.enigma.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("data is not found");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
