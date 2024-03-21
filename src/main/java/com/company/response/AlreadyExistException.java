package com.company.response;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message){super(message);}
}