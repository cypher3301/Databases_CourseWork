package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import com.example.demo.dao.entity.embeddable.Address;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "driver")
@NoArgsConstructor
@Table(name = "driver", catalog = "postOffice", schema = "public")
public class Driver extends Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    protected long id;
//
//
//    @NotNull
//    @Column(name = "firstname", nullable = false)
//    private String firstname;
//
//    @NotNull
//    @Column(name = "patronymic", nullable = false)
//    private String patronymic;
//
//    @NotNull
//    @Column(name = "surname", nullable = false)
//    private String surname;
//
//    @NotNull
//    @Column(name = "phone", nullable = false)
//    private String phone;
//
//    @NotNull
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "price_card_number", length = 16)
//    private long priceCardNumber;
//
//    @NotNull
//    private Address address;


    @OneToOne
    @JoinColumn(name = "car_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "driver_car"))
    private Car car;

    @OneToMany(mappedBy = "driver")
    private Collection<Waybill> waybills;

    /**
     * Equals and HashCode override in Employee basic class
     * */

}
