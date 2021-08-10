package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "car")
@Table(name = "car", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NaturalId
    @Column(name = "car_number", length = 10)
    private String carNumber;

    @NotNull
    @Column(name = "mark", nullable = false,length = 32)
    private String mark;


    @Column(name = "transported_volume", nullable = false)
    @NotNull
    @Min(value = 1,message = "transported volume is less than required")
    @Max(value = 50, message = "transported volume is more than necessary")
    @ColumnDefault(value = "9")
    private double transportedVolume;

    @Column(name = "transported_weight", nullable = false)
    @NotNull
    @Min(value = 20,message = "transported weight is less than required")
    @Max(value = 3000, message = "transported weight is more than necessary")
    @ColumnDefault(value = "50")
    private double transportedWeight;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return getCarNumber() != null ? getCarNumber().equals(car.getCarNumber()) : car.getCarNumber() == null;
    }

    @Override
    public int hashCode() {
        return getCarNumber() != null ? getCarNumber().hashCode() : 0;
    }

}
