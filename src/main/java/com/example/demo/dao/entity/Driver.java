package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "driver")
@NoArgsConstructor
@Table(name = "driver", catalog = "postOffice", schema = "public")
public class Driver extends Employee {


    @OneToOne
    @JoinColumn(name = "car_id",referencedColumnName = "car_number",foreignKey = @ForeignKey(name = "driver_car"))
    private Car car;

    @OneToMany(mappedBy = "driver")
    private Collection<Waybill> waybills;

    /*
     * Equals and HashCode override in Employee basic class
     * */

}
