package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;


@Entity(name = "package")
@NoArgsConstructor
@Data
@Table(name = "package", catalog = "postOffice", schema = "public")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id", table = "package")
    protected long id;

//
//    @ElementCollection
//    @CollectionTable(
//            name = "package_id",
//            joinColumns = @JoinColumn(name = "package_id")
//    )
//    protected Collection<ClientsPackages> clientsPackages = new ArrayList<>();





}
