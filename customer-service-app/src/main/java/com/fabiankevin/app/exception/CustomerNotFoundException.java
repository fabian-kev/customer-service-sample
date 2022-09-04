package com.fabiankevin.app.exception;


import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends CustomerException {

    public CustomerNotFoundException(HttpStatus status, String message, String code) {
        super(status, message, code);
    }
}
