package com.example.demo.entity;

import com.example.demo.entity.embeddable.Address;
import com.example.demo.entity.status.PackageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;



@Entity(name = "package")
@NoArgsConstructor
@Data
@Table(name = "package", catalog = "postOffice", schema = "public")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id", table = "package")
    protected long package_id;

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
    protected Address actualAddress;

    @NotNull
    @Column(name = "from_dateTime", nullable = false)
    protected Timestamp datetimeSendingPackage;


    @Column(name = "to_dateTime")
    protected Timestamp datetimeReceiptPackage;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operator_id", foreignKey = @ForeignKey(name = "person_id_fk"))
    protected Operator operator;
}
