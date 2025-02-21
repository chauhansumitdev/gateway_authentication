package com.example.demo.controller;


import com.example.demo.dto.AddressDTO;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService= addressService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getByID(@PathVariable UUID id){
        return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AddressDTO> createPrdduct(@RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.createAddress(addressDTO), HttpStatus.CREATED);
    }

}
