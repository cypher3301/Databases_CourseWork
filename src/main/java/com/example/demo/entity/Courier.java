package com.example.demo.entity;

import com.example.demo.entity.abstraction.Employee;
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
public class Courier extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id", table = "courier")
    protected long id;

    @NotNull
    @Column(name = "car_number", table = "courier", nullable = false)
    protected String numberOfCar;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    protected Collection<Invoice> invoice = new ArrayList<>();



}
