package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name = "sender_recipient_package")
@Immutable
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class ClientsPackages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @NotNull
    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "client_sender_id",nullable = false
    )
    protected Client sender;

    @NotNull
    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "client_recipient_id",nullable = false
    )
    protected Client recipient;

    @NotNull
    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "package_id",nullable = false
    )
    protected Package packages;

}



