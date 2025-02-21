package com.example.demo.exceptionhandler;

public class AddressException extends RuntimeException{

    public AddressException(String message){
        super(message);
    }
}
