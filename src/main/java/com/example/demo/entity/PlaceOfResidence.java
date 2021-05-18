package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode(callSuper = true)
@Embeddable
@Data
@NoArgsConstructor
public class PlaceOfResidence extends Local{
    protected String houseNumber;
    protected String apartmentNumber;
    protected String zipcode;

    public PlaceOfResidence(String region, String city, String street, String houseNumber, String apartmentNumber, String zipcode) {
        super(region, city, street);
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipcode = zipcode;
    }

    public PlaceOfResidence(String region, String city, String street, String houseNumber, String apartmentNumber) {
        super(region, city, street);
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public PlaceOfResidence(String houseNumber, String apartmentNumber, String zipcode) {
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipcode = zipcode;
    }

    public PlaceOfResidence(String region, String city, String houseNumber, String apartmentNumber) {
        super(region, city);
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }
}

