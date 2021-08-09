package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;


@Entity(name = "client")
@Table(name = "client", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Client extends Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//
//    @NotNull
//    @Column(name = "firstname",nullable = false)
//    private String firstname;
//
//    @NotNull
//    @Column(name = "patronymic",nullable = false)
//    private String patronymic;
//
//    @NotNull
//    @Column(name = "surname",nullable = false)
//    private String surname;
//
//    @NotNull
//    @Column(name = "phone",nullable = false)
//    private String phone;
//
//    @NotNull
//    @Column(name = "email")
//    private String email;
//
//    @NotNull
//    @Column(name="password")
//    private byte[] password;


    @OneToMany(mappedBy = "clientSender")
    private Collection<Invoice> sends;

    @OneToMany(mappedBy = "clientRecipient")
    private Collection<Invoice> recipes;

    @OneToMany
    private Collection<Station> stations;


    /**
     * Equals and HashCode override in Person basic class
     * */
}

