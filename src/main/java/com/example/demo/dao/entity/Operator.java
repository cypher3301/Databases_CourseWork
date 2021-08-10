package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import com.example.demo.dao.entity.embeddable.Authentication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "operator")
@Table(name = "operator", catalog = "postOffice", schema = "public")
@AttributeOverrides({
        @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false))
})
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




    @ManyToOne
    @JoinColumn(name = "station_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "operators_station"))
    private Station station;





    /*
     * Equals and HashCode override in Employee basic class
     * */
}
