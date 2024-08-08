package com.baseapp.it_support_api.exception;

public class TechnicianNotFoundException extends RuntimeException{
    public TechnicianNotFoundException(String message) {
        super(message);
    }

}
