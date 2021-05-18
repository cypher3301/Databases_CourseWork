package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "station")
@Table(name = "station", schema = "public", catalog = "postOffice")
@NoArgsConstructor
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "station_id", table = "station")
    protected long id;

    @NotNull
    @Column(name = "address")
    protected Address address;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "station")
    protected Collection<Operator> operators = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "station")
//    protected Collection<Invoice> invoices = new ArrayList<>();
}
