package com.company.response;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){super(message);}
}