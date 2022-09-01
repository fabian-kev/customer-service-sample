package com.fabiankevin.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class CustomerException extends RuntimeException {
    private HttpStatus status;
    private String code;

    public CustomerException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public CustomerException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public CustomerException(HttpStatus status, String message, String code, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }


    public CustomerException(HttpStatus status, String message, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }


    public CustomerException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;;
    }


}
