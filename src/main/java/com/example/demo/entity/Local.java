package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Local {
    protected String region;
    protected String city;
    protected String street;

    public Local(String region, String city) {
        this.region = region;
        this.city = city;
    }
}
