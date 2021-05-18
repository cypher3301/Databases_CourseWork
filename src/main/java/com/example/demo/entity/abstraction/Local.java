package com.example.demo.entity.abstraction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Local {
    @Column(name = "region", nullable = false, length = 24)
    protected String region;
    @Column(name = "city",   nullable = false, length = 24)
    protected String city;
    @Column(name = "street",                   length = 24)
    protected String street;


    public Local(String region, String city) {
        this.region = region;
        this.city = city;
    }
}
