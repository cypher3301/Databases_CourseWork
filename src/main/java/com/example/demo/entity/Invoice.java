package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "invoice")
@Data
@NoArgsConstructor
@Table(name = "invoice", catalog = "postOffice", schema = "public")
//@Check(constraints = "loadingDateAndTime<deliveryDateAndTime")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    protected long invoice_id;

    @NotNull
    @Column(name = "from_dateTime", nullable = false)
    protected Timestamp loadingDateAndTime;

    @Column(name = "to_dateTime")
    protected Timestamp deliveryDateAndTime;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "invoice_package")
    protected Collection<Package> packages = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "courier_id",foreignKey = @ForeignKey(name = "fk_invoice_courier_id"))
    protected Courier courier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id",foreignKey = @ForeignKey(name = "fk_invoice_operator_id"))
    protected Operator operator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startStation_id",foreignKey = @ForeignKey(name = "fk_invoice_startStation_id"))
    protected Station startStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endStation_id",foreignKey = @ForeignKey(name = "fk_invoice_endStation_id"))
    protected Station endStation;

}
