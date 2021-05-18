package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "operator")
@Data
@NoArgsConstructor
@Table(name = "operator", catalog = "postOffice", schema = "public")
//@AttributeOverrides({
//        @AttributeOverride(name = "firstname",            column = @Column(name = "firstname",     table = "operator", nullable = false)),
//        @AttributeOverride(name = "middlename",           column = @Column(name = "middlename",    table = "operator", nullable = false)),
//        @AttributeOverride(name = "lastname",             column = @Column(name = "lastname",      table = "operator", nullable = false)),
//        @AttributeOverride(name = "phoneNumber.phone",    column = @Column(name = "phone_number",  table = "operator", nullable = false, length = 12))
//})
public class Operator extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operator_id", table = "operator")
    protected Long id;

//    @NotNull
//    @AttributeOverrides({
//            @AttributeOverride(name = "region",             column = @Column(name = "region",            table = "operator", nullable = false, length = 24)),
//            @AttributeOverride(name = "city",               column = @Column(name = "city",              table = "operator", nullable = false, length = 24)),
//            @AttributeOverride(name = "street",             column = @Column(name = "street",            table = "operator", nullable = false, length = 24)),
//            @AttributeOverride(name = "houseNumber",        column = @Column(name = "house_number",      table = "operator", nullable = false, length = 8)),
//            @AttributeOverride(name = "apartmentNumber",    column = @Column(name = "apartment_number",  table = "operator", nullable = false, length = 8)),
//            @AttributeOverride(name = "zipcode",            column = @Column(name = "zipcode",           table = "operator", nullable = false, length = 5))
//    })
//    protected PlaceOfResidence placeOfResidence;
//
//    @NotNull
//    @Column(name = "identification_code", table = "operator", nullable = false,length = 10, unique = true)
//    protected long identificationCode;
//
//    @Column(name = "price_card_number", table = "operator", length = 16)
//    protected long priceCardNumber;
//
//    @Column(name = "price", table = "operator")
//    protected double price;

    @Column(name = "login", table = "operator", length = 8)
    protected String login;

    @Column(name="password", table = "operator")
    private byte[] password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operator")
    protected Collection<Package> packages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operator")
    protected Collection<Invoice> invoices = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operator")
    protected Collection<WorkShift> workShifts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    protected Station station;
//
//    public Operator(String firstname, String middlename, String lastname, PhoneNumber phoneNumber, @NotNull PlaceOfResidence placeOfResidence, @NotNull long identificationCode, long priceCardNumber) {
//        super(firstname, middlename, lastname, phoneNumber);
//        this.placeOfResidence = placeOfResidence;
//        this.identificationCode = identificationCode;
//        this.priceCardNumber = priceCardNumber;
//    }
//
//    public Operator(String firstname, String middlename, String lastname, PhoneNumber phoneNumber, @NotNull PlaceOfResidence placeOfResidence, @NotNull long identificationCode) {
//        super(firstname, middlename, lastname, phoneNumber);
//        this.placeOfResidence = placeOfResidence;
//        this.identificationCode = identificationCode;
//    }
}
