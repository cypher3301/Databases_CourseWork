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
    @AttributeOverrides({
            @AttributeOverride(name = "region",             column = @Column(name = "from_region",           table = "invoice", nullable = false)),
            @AttributeOverride(name = "city",               column = @Column(name = "from_city",             table = "invoice", nullable = false)),
            @AttributeOverride(name = "street",             column = @Column(name = "from_street",           table = "invoice")),
            @AttributeOverride(name = "postOfficeNumber",   column = @Column(name = "from_post_office_number", table = "invoice", nullable = false))
    })
    protected Address loadingAddress;
    @NotNull
    @Column(name = "from_dateTime", nullable = false)
    protected Timestamp loadingDateAndTime;

    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "region",             column = @Column(name = "to_region",           table = "invoice", nullable = false)),
            @AttributeOverride(name = "city",               column = @Column(name = "to_city",             table = "invoice", nullable = false)),
            @AttributeOverride(name = "street",             column = @Column(name = "to_street",           table = "invoice")),
            @AttributeOverride(name = "postOfficeNumber",   column = @Column(name = "to_post_office_number", table = "invoice", nullable = false))
    })
    protected Address deliveryAddress;

    @Column(name = "to_dateTime")
    protected Timestamp deliveryDateAndTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    protected Courier courier;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Operator operator;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "invoice")
    protected Collection<Package> packages = new ArrayList<>();

    public Invoice(@NotNull Address loadingAddress, @NotNull Address deliveryAddress) {
        this.loadingAddress = loadingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public Invoice(@NotNull Address loadingAddress, @NotNull Timestamp loadingDateAndTime, @NotNull Address deliveryAddress) {
        this.loadingAddress = loadingAddress;
        this.loadingDateAndTime = loadingDateAndTime;
        this.deliveryAddress = deliveryAddress;
    }
}
