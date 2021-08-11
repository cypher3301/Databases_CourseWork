package com.example.demo.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @Column(name = "car_number", unique = true, length = 10)
    @NotBlank(message = "Car number cannot be null or empty")
    private String carNumber;

    @Column(name = "mark", length = 32)
    private String mark;


    @Column(name = "transported_volume", nullable = false)
    @ColumnDefault(value = "9")
    @DecimalMin(value = "1",           message = "Car transported volume is less than required")
    @DecimalMax(value = "50",          message = "Car transported volume is more than necessary")
    @Digits(integer = 2, fraction = 3, message = "Car volume can be up to 50 ")
    @Positive(                         message = "Car must have volume greater than 1")
    private double transportedVolume;

    @Column(name = "transported_weight", nullable = false)
    @ColumnDefault(value = "50")
    @DecimalMin(value = "20",          message = "Car transported weight is less than required")
    @DecimalMax(value = "3000",        message = "Car transported weight is more than necessary")
    @Digits(integer = 4, fraction = 3, message = "Car volume can be up to 50 " )
    @Positive(                         message = "Car must have weight greater than 1")
    private double transportedWeight;


    public Car(long id, String carNumber, String mark, double transportedVolume, double transportedWeight) {
        this.id = id;
        this.carNumber = carNumber;
        this.mark = mark;
        this.transportedVolume = transportedVolume;
        this.transportedWeight = transportedWeight;
    }

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
