package com.company.exception;

public class WrongException extends RuntimeException {
    public WrongException(String message) {
        super(message);
    }
}