package com.example.demo.dto;

import com.example.demo.oth.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private Address address;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}