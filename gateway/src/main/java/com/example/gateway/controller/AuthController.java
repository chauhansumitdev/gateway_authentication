package com.example.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    // -------------------- TESTING ENDPOINT ---------------------- //

    @PostMapping("/gateway/test")
    public ResponseEntity<String> register(){
        return new ResponseEntity<>("GATEWAY TESTING ENDPINT", HttpStatus.OK);
    }



}
