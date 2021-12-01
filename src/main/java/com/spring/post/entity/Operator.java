package com.spring.post.entity;

import com.spring.post.entity.ancestor.Employee;
import com.spring.post.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "operator")
@Table(name = "operator", schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_operator_login", columnNames = "login")
)
@NoArgsConstructor
@Getter
@Setter
public class Operator extends Employee {


    @Column(name = "login", nullable = false, unique = true)
    private String login;


    @OneToMany(mappedBy = "operator")
    private Collection<Invoice> invoices;

    @OneToMany(mappedBy = "operator")
    private Collection<Waybill> waybills;

    @OneToMany(mappedBy = "operator")
    private Collection<WorkShift> workShifts;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "operators_station"))
    @NotNull(message = "Operator station cannot be null")
    private Station station;

    public Operator(Long id, String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Station station) {
        super(id, firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.station = station;
    }

    public Operator(String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Station station) {
        super(firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.station = station;
    }

    /*
     * Equals and HashCode override in Employee basic class
     * */

    @Override
    public String toString() {
        return "Operator{" +
                "login='" + login + '\'' +
                ", invoices=" + invoices +
                ", waybills=" + waybills +
                ", workShifts=" + workShifts +
                ", station=" + station +
                ", email='" + email + '\'' +
                ", priceCardNumber='" + priceCardNumber + '\'' +
                ", address=" + address +
                ", identificationCode='" + identificationCode + '\'' +
                ", position=" + position +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

