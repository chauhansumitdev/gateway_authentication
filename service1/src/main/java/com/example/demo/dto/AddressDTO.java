package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;


@Data
public class AddressDTO {

    private UUID id;

    @NotBlank
    private String city;

    @NotBlank
    private String street;
}
