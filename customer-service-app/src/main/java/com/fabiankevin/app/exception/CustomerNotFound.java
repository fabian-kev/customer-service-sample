package com.fabiankevin.app.exception;


import org.springframework.http.HttpStatus;

public class CustomerNotFound extends CustomerException {

    public CustomerNotFound(HttpStatus status, String message, String code) {
        super(status, message, code);
    }
}
