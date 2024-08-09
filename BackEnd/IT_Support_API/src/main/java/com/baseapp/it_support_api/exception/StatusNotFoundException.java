package com.baseapp.it_support_api.exception;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException(String message) {
        super(message);
    }

}
