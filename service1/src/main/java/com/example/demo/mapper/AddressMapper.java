package com.example.demo.mapper;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setId(address.getId());
        return addressDTO;
    }


    public Address toDAO(AddressDTO addressDTO){
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        return address;
    }

}
