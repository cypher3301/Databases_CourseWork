package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "car", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "mark")
    private String mark;
    @Column(name = "transported_volume")
    private double transportedVolume;
    @Column(name = "transported_weight")
    private double transportedWeight;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return getNumber() != null ? getNumber().equals(car.getNumber()) : car.getNumber() == null;
    }

    @Override
    public int hashCode() {
        return getNumber() != null ? getNumber().hashCode() : 0;
    }

}
