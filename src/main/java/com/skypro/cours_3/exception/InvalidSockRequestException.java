package com.skypro.cours_3.exception;

public class InvalidSockRequestException extends RuntimeException{
    public InvalidSockRequestException(String message) {
        super(message);
    }
}
