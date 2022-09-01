package com.fabiankevin.app.exception;

public class CustomerExistsException extends CustomerException {
    public CustomerExistsException(String message) {
        super(message);
    }
}
