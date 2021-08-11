package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import com.example.demo.dao.entity.embeddable.Authentication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "operator")
@Table(name = "operator", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Operator extends Employee {

    @Embedded
    private Authentication authentication;


    @OneToMany(mappedBy = "operator")
    private Collection<Invoice> invoices;

    @OneToMany(mappedBy = "operator")
    private Collection<Waybill> waybills;

    @OneToMany(mappedBy = "operator")
    private Collection<WorkShift> workShifts;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "operators_station"))
    @NotNull(message = "Operator station cannot be null")
    private Station station;


    /*
     * Equals and HashCode override in Employee basic class
     * */
}
