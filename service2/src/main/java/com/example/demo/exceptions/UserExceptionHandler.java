package com.example.demo.exceptions;

import com.example.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleException(UserException ex){
        ErrorResponse userException = new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(userException,HttpStatus.NOT_FOUND);
    }
}
