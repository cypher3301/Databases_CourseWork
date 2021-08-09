package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import com.example.demo.dao.entity.embeddable.Address;
import com.example.demo.dao.entity.embeddable.Authentication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    protected Long id;
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
//    protected long priceCardNumber;
//
//    private Address address;


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





    /**
     * Equals and HashCode override in Employee basic class
     * */
}
