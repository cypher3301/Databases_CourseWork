package com.example.demo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client", catalog ="postOffice",schema = "public")
@Data
public class ClientRecipient extends Client {


    public ClientRecipient() {
    }



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client", cascade = CascadeType.ALL)
    protected Collection<Package> packagesRecipient = new ArrayList<>();
}
