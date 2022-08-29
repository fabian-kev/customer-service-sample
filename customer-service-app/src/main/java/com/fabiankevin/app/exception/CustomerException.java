package com.fabiankevin.app.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException {
    private HttpStatus status;
}
