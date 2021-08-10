package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull( message = "Client stations cannot be null")
    @Size(    message = "Maximum 5 stations", min = 1, max = 5)
    @OneToMany
    private Collection<Station> stations;



    /*
     * Equals and HashCode override in Person basic class
     * */
}

