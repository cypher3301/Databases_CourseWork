package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class PlaceOfResidence {
    protected String region;
    protected String city;
    protected String street;
    protected String houseNumber;
    protected String apartmentNumber;
    protected String zipcode;


    public PlaceOfResidence(String region, String city, String street) {
        this.region = region;
        this.city = city;
        this.street = street;
    }

    public PlaceOfResidence(String region, String city, String street, String houseNumber, String apartmentNumber) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public PlaceOfResidence(String region, String city, String street, String houseNumber, String apartmentNumber, String zipcode) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipcode = zipcode;
    }
}

