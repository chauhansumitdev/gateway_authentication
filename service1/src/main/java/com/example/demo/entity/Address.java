package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

}
