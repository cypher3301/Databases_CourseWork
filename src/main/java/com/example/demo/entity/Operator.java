package com.example.demo.entity;

import com.example.demo.entity.abstraction.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "operator")
@Data
@NoArgsConstructor
@Table(name = "operator", catalog = "postOffice", schema = "public")
public class Operator extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operator_id", table = "operator")
    protected Long id;


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
    @JoinColumn(name = "station_id", foreignKey = @ForeignKey(name = "station_id_fk"))
    protected Station station;

}
