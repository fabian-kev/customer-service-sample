package com.fabiankevin.app.exception.handler;

import com.fabiankevin.app.exception.CustomerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ErrorHandler {

    private final ErrorResponseResolver errorResponseResolver;

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> handleCustomerExceptions(CustomerException e){
        ErrorResponse errorResponse = errorResponseResolver.resolve(e);
        return ResponseEntity.status(e.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomerExceptions(Exception e){
        log.error("handleCustomerExceptions ", e);
        ErrorResponse resolve = errorResponseResolver.resolve(new RuntimeException());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(resolve);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> andleValidationExceptions(
            MethodArgumentNotValidException ex) {

        String errorMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .error(ErrorResponse.Error.builder()
                                .timestamp(OffsetDateTime.now())
                                .message(errorMessages)
                        .build())
                .build());
    }
}
