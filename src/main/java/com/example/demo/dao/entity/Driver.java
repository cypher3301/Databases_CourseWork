package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity(name = "driver")
@Table(name = "driver", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Driver extends Employee {


    @OneToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false, referencedColumnName = "car_number", foreignKey = @ForeignKey(name = "driver_car"))
    @NotNull(message = "Driver car cannot be null")
    private Car car;

    @OneToMany(mappedBy = "driver")
    @Size(    message = "Driver waybills must be greater or equal 0")
    @NotNull( message = "Driver waybills cannot be null")
    private Collection<Waybill> waybills;


    /*
     * Equals and HashCode override in Employee basic class
     * */

}
