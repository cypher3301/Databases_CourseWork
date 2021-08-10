package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Entity(name = "client")
@Table(name = "client", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Client extends Person {




    @OneToMany(mappedBy = "clientSender")
    private Collection<Invoice> sends;

    @OneToMany(mappedBy = "clientRecipient")
    private Collection<Invoice> recipes;

    @OneToMany
    private Collection<Station> stations;



    /*
     * Equals and HashCode override in Person basic class
     * */
}

