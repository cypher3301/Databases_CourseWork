package com.example.demo.entity;

import com.example.demo.entity.abstraction.Human;
import com.example.demo.entity.embeddable.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "client")
@Data
@NoArgsConstructor
@Table(name = "client", catalog ="postOffice",schema = "public")
public class Client extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id", table = "client")
    protected Long id;

    @NotNull
    protected Address address;

}


