package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "client")
@Data
@NoArgsConstructor
@Table(name = "client", catalog ="postOffice",schema = "public")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", table = "client")
    protected long id;
//
//    @ElementCollection
//    @CollectionTable(
//            name = "client_sender_id",
//            joinColumns = @JoinColumn(name = "client_sender_id")
//    )
//    protected Collection<ClientsPackages> clientsPackagesSends = new ArrayList<>();
//
//    @ElementCollection
//    @CollectionTable(
//            name = "client_recipient_id",
//            joinColumns = @JoinColumn(name = "client_recipient_id")
//    )
//    protected Collection<ClientsPackages> clientsPackagesRecipients = new ArrayList<>();



}


