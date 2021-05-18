package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@AllArgsConstructor
@Data
@NoArgsConstructor
public abstract class Human {
    @NotNull
    @Column(name = "firstname", nullable = false)
    protected String firstname;

    @NotNull
    @Column(name = "middlename", nullable = false)
    protected String middlename;

    @NotNull
    @Column(name = "lastname", nullable = false)
    protected String lastname;


    @NotNull
    @AttributeOverride(name = "phone", column = @Column(name = "phone"))
    @Column(name = "phone", nullable = false)
    protected PhoneNumber phoneNumber;


}
