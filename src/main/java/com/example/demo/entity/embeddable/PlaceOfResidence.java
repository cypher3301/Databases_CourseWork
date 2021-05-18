package com.example.demo.entity.embeddable;

import com.example.demo.entity.abstraction.Local;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode(callSuper = true)
@Embeddable
@Data
@NoArgsConstructor
public class PlaceOfResidence extends Local {
    @Column(name = "house_number",      nullable = false, length = 8)
    protected String houseNumber;
    @Column(name = "apartment_number",  nullable = false, length = 8)
    protected String apartmentNumber;
    @Column(name = "zipcode",           nullable = false, length = 5)
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

