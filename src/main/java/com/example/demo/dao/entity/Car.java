package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "car")
@Table(name = "car", catalog = "postOffice", schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_car_number",columnNames = "car_number"))
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @NaturalId
    @Column(name = "car_number", length = 10)
    @NotBlank(message = "Car number cannot be null or empty")
    private String carNumber;

    @Column(name = "mark", length = 32)
    private String mark;


    @Column(name = "transported_volume_m3", nullable = false)
    @ColumnDefault(value = "9")
    @DecimalMin(value = "1",           message = "Car transported volume is less than required")
    @DecimalMax(value = "50",          message = "Car transported volume is more than necessary")
    @Digits(integer = 2, fraction = 3, message = "Car volume can be up to 50 ")
    @Positive(                         message = "Car must have volume greater than 1")
    private double transportedVolume;

    @Column(name = "transported_weight_kg", nullable = false)
    @ColumnDefault(value = "50")
    @DecimalMin(value = "20",          message = "Car transported weight is less than required")
    @DecimalMax(value = "3000",        message = "Car transported weight is more than necessary")
    @Digits(integer = 4, fraction = 3, message = "Car volume can be up to 50 " )
    @Positive(                         message = "Car must have weight greater than 1")
    private double transportedWeight;


    public Car(String carNumber, String mark, double transportedVolume, double transportedWeight) {
        this.carNumber = carNumber;
        this.mark = mark;
        this.transportedVolume = transportedVolume;
        this.transportedWeight = transportedWeight;
    }


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
