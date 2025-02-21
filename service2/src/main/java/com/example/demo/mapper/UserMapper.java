package com.example.demo.mapper;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.oth.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMapper {


    public User toDAO(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddressID(userDTO.getAddress().getId());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO toDTO(User user, Address address){
        log.info("IN MAPPER :" + user.getId() + "  "+ address.getId() );
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(address);
        return  userDTO;
    }
}
