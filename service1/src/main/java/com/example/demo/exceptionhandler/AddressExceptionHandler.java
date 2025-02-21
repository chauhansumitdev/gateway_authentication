package com.example.demo.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AddressExceptionHandler {

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<ErrorResponse> handleException(AddressException ex) {
        log.info("HANDLING EXCEPTION IN GLOBAL EXCEPTION HANDLER");
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
