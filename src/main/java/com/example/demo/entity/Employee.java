package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@AttributeOverrides({
        @AttributeOverride(name = "firstname", column = @Column(name = "firstname", nullable = false)),
        @AttributeOverride(name = "middlename", column = @Column(name = "middlename", nullable = false)),
        @AttributeOverride(name = "lastname", column = @Column(name = "lastname", nullable = false)),
        @AttributeOverride(name = "phoneNumber.phone", column = @Column(name = "phone_number", nullable = false, length = 12))
})
public abstract class Employee extends Human {
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "region", column = @Column(name = "region", nullable = false, length = 24)),
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false, length = 24)),
            @AttributeOverride(name = "street", column = @Column(name = "street", nullable = false, length = 24)),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "house_number", nullable = false, length = 8)),
            @AttributeOverride(name = "apartmentNumber", column = @Column(name = "apartment_number", nullable = false, length = 8)),
            @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode", nullable = false, length = 5))
    })
    protected PlaceOfResidence placeOfResidence;

    @NotNull
    @Column(name = "identification_code", nullable = false,length = 10, unique = true)
    protected long identificationCode;

    @Column(name = "price_card_number", length = 16)
    protected long priceCardNumber;

    @Column(name = "price")
    protected double price;

    public Employee(String firstname, String middlename, String lastname, PhoneNumber phoneNumber, PlaceOfResidence placeOfResidence, long identificationCode, long priceCardNumber, double price) {
        super(firstname, middlename, lastname, phoneNumber);
        this.placeOfResidence = placeOfResidence;
        this.identificationCode = identificationCode;
        this.priceCardNumber = priceCardNumber;
        this.price = price;
    }
}
