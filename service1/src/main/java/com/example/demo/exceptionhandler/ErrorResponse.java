package com.example.demo.exceptionhandler;


import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private Integer statusCode;

    public ErrorResponse(String message, Integer statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }
}
