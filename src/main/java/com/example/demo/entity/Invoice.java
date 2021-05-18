package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "invoice")
@Data
@NoArgsConstructor
@Table(name = "invoice", catalog = "postOffice", schema = "public")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    protected long id;

    @NotNull
    @Column(name = "from_dateTime", nullable = false)
    protected Timestamp loadingDateAndTime;

    @Column(name = "to_dateTime")
    protected Timestamp deliveryDateAndTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    protected Courier courier;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Operator operator;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "invoice")
    protected Collection<Package> packages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    protected Station startingStation;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Station endStation;
//
//    public Invoice(@NotNull Address loadingAddress, @NotNull Address deliveryAddress) {
//        this.loadingAddress = loadingAddress;
//        this.deliveryAddress = deliveryAddress;
//    }
//
//    public Invoice(@NotNull Address loadingAddress, @NotNull Timestamp loadingDateAndTime, @NotNull Address deliveryAddress) {
//        this.loadingAddress = loadingAddress;
//        this.loadingDateAndTime = loadingDateAndTime;
//        this.deliveryAddress = deliveryAddress;
//    }
}
