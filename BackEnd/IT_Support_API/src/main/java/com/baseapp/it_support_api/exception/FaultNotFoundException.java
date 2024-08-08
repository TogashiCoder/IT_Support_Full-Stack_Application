package com.baseapp.it_support_api.exception;

public class FaultNotFoundException extends RuntimeException{
    public FaultNotFoundException(String message) {
        super(message);
    }

}
