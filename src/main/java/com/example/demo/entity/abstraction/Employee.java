package com.example.demo.entity.abstraction;

import com.example.demo.entity.embeddable.PhoneNumber;
import com.example.demo.entity.embeddable.PlaceOfResidence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Employee extends Human {
    @NotNull
    protected PlaceOfResidence placeOfResidence;

    @NotNull
    @Column(name = "identification_code", nullable = false, length = 10, unique = true)
    protected long identificationCode;

    @Column(name = "price_card_number",                     length = 16)
    protected long priceCardNumber;

    @Column(name = "price")
    protected double price;

}
