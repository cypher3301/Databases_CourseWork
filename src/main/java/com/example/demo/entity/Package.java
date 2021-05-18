package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;



@Entity(name = "packages")
@NoArgsConstructor
@Data
@Table(name = "package", catalog = "postOffice", schema = "public")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id", table = "package")
    protected long id;

    @NotNull
    @Column(name = "status", table = "package", nullable = false)
    @Enumerated(EnumType.STRING)
    protected PackageStatus status;

    @NotNull
    @Column(name = "weight",    table = "package",nullable = false)
    protected double weight;
    @NotNull
    @Column(name = "volume",    table = "package",nullable = false)
    protected double volume;
    @NotNull
    @Column(name = "insurance", table = "package",nullable = false)
    protected double insurance;
    @NotNull
    @Column(name = "quantity",  table = "package",nullable = false)
    protected double quantity;



    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "region",             column = @Column(name = "region",             table = "package", nullable = false)),
            @AttributeOverride(name = "city",               column = @Column(name = "city",               table = "package", nullable = false)),
            @AttributeOverride(name = "street",             column = @Column(name = "street",             table = "package")),
            @AttributeOverride(name = "postOfficeNumber",   column = @Column(name = "post_office_number", table = "package", nullable = false))
    })
    protected Address actualAddress;

    @NotNull
    @Column(name = "from_dateTime", nullable = false)
    protected Timestamp datetimeSendingPackage;


    @Column(name = "to_dateTime")
    protected Timestamp datetimeReceiptPackage;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    protected ClientSender clientSender;
//
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    protected ClientRecipient clientRecipient;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    protected Operator operator;
//
    @ManyToOne(fetch = FetchType.LAZY)
    protected Invoice invoice;

//
//
//    public Package(double weight, double volume, double insurance, double quantity, Address deliveryAddress, Timestamp datetimeReceiptPackage) {
//        this.weight = weight;
//        this.volume = volume;
//        this.insurance = insurance;
//        this.quantity = quantity;
////        this.deliveryAddress = deliveryAddress;
//        this.datetimeReceiptPackage = datetimeReceiptPackage;
//    }








}
