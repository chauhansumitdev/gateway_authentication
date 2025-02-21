package com.example.demo.service;


import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import com.example.demo.exceptionhandler.AddressException;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper){
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    public AddressDTO createAddress(AddressDTO addressDTO){
        log.info("CREATING ADDRESS");
        return addressMapper.toDTO(addressRepository.save(addressMapper.toDAO(addressDTO)));
    }

    public AddressDTO getById(UUID id){
        log.info("FETCHING ADDRESS");
        Address address = addressRepository.findById(id).orElseThrow(()-> new AddressException("ADDRESS DOES NOT EXIST"));

        return addressMapper.toDTO(address);
    }
}
