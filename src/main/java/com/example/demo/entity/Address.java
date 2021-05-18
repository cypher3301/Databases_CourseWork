package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;

@EqualsAndHashCode(callSuper = true)
@Embeddable
@Data
@NoArgsConstructor
public class Address extends Local {

    protected short postOfficeNumber;


    public Address(short postOfficeNumber) {
        this.postOfficeNumber = postOfficeNumber;
    }

    public Address(String region, String city, String street, short postOfficeNumber) {
        super(region, city, street);
        this.postOfficeNumber = postOfficeNumber;
    }

    public Address(String region, String city, short postOfficeNumber) {
        super(region, city);
        this.postOfficeNumber = postOfficeNumber;
    }
}
