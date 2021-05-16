package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseEntity {
    protected String firstname;
    protected String middlename;
    protected String lastname;

    @AttributeOverride(name = "phone", column = @Column(name = "phone"))
    protected PhoneNumber phoneNumber;


}
