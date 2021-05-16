package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

    protected String region;
    protected String city;
    protected String street;
    protected String postOfficeNumber;


    public Address(String region, String city, String street, String postOfficeNumber) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.postOfficeNumber = postOfficeNumber;
    }

    public Address(String region, String city, String postOfficeNumber) {
        this.region = region;
        this.city = city;
        this.postOfficeNumber = postOfficeNumber;
    }
}
