package com.example.demo.oth;
import lombok.Data;

import java.util.UUID;

@Data
public class Address {
    private UUID id;
    private String city;
    private String street;
}
