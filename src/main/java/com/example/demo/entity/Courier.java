package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "courier")
@Data
@NoArgsConstructor
@Table(name = "courier", catalog = "postOffice", schema = "public")
//@AttributeOverrides({
//        @AttributeOverride(name = "firstname",            column = @Column(name = "firstname",     table = "courier", nullable = false)),
//        @AttributeOverride(name = "middlename",           column = @Column(name = "middlename",    table = "courier", nullable = false)),
//        @AttributeOverride(name = "lastname",             column = @Column(name = "lastname",      table = "courier", nullable = false)),
//        @AttributeOverride(name = "phoneNumber.phone",    column = @Column(name = "phone_number",  table = "courier", nullable = false))
//})
public class Courier extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id", table = "courier")
    protected long id;
//
//    @NotNull
//    @AttributeOverrides({
//            @AttributeOverride(name = "region",             column = @Column(name = "region",            table = "courier", nullable = false)),
//            @AttributeOverride(name = "city",               column = @Column(name = "city",              table = "courier", nullable = false)),
//            @AttributeOverride(name = "street",             column = @Column(name = "street",            table = "courier", nullable = false)),
//            @AttributeOverride(name = "houseNumber",        column = @Column(name = "house_number",      table = "courier", nullable = false)),
//            @AttributeOverride(name = "apartmentNumber",    column = @Column(name = "apartment_number",  table = "courier", nullable = false)),
//            @AttributeOverride(name = "zipcode",            column = @Column(name = "zipcode",           table = "courier", nullable = false))
//    })
//    protected PlaceOfResidence placeOfResidence;
//
//    @NotNull
//    @Column(name = "identification_code", table = "courier", nullable = false,length = 10, unique = true)
//    protected long identificationCode;
//
//    @Column(name = "price_card_number", table = "courier", length = 16)
//    protected long priceCardNumber;
//
//
//    @Column(name = "price", table = "courier")
//    private double price;

    @NotNull
    @Column(name = "car_number", table = "courier", nullable = false)
    protected String numberOfCar;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    protected Collection<Invoice> invoice = new ArrayList<>();


//
//    public Courier(String firstname, String middlename, String lastname, PhoneNumber phoneNumber, @NotNull PlaceOfResidence placeOfResidence, String numberOfCar) {
//        super(firstname, middlename, lastname, phoneNumber);
//        this.placeOfResidence = placeOfResidence;
//        this.numberOfCar = numberOfCar;
//    }
}
