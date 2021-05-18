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
public class Address extends Local {

    @Column(name = "post_office_number", nullable = false)
    protected short postOfficeNumber;


    public Address(String region, String city, String street, short postOfficeNumber) {
        super(region, city, street);
        this.postOfficeNumber = postOfficeNumber;
    }

    public Address(String region, String city, short postOfficeNumber) {
        super(region, city);
        this.postOfficeNumber = postOfficeNumber;
    }
}
