package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "clients_packages")

//@Table
//@Embeddable
//@Immutable
@NoArgsConstructor
@Data
public class ClientsPackages {
//
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToOne
    @JoinColumn(
            name = "client_sender_id"
    )
    protected Client sender;

    @ManyToOne
    @JoinColumn(
            name = "client_recipient_id"
    )
    protected Client recipient;

    @ManyToOne
    @JoinColumn(
            name = "package_id"
    )
    protected Package packages;
//
    public ClientsPackages(Client sender, Client recipient, Package packages) {
        this.sender = sender;
        this.recipient = recipient;
        this.packages = packages;
    }
}
