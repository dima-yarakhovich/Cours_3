package com.skypro.cours_3.exception;


public class InSufficientSockQuantity extends RuntimeException{

    public InSufficientSockQuantity(String message) {
        super(message);
    }
}