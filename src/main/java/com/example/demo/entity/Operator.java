package com.example.demo.entity;

import com.example.demo.entity.ancestor.Employee;
import com.example.demo.entity.embeddable.Address;
import com.example.demo.entity.embeddable.Authentication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "operator")
@Table(name = "operator", catalog = "postOffice", schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_operator_login", columnNames = "login")
)
@NoArgsConstructor
@Getter
@Setter
public class Operator extends Employee {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login",
                    column = @Column(name = "login", length = 12, nullable = false)),
            @AttributeOverride(name = "password",
                    column = @Column(name = "password", length = 12, nullable = false))
    })
    private Authentication authentication;


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

    public Operator(Long id, String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Authentication authentication, Station station) {
        super(id, firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.authentication = authentication;
        this.station = station;
    }

    public Operator(String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Authentication authentication, Station station) {
        super(firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.authentication = authentication;
        this.station = station;
    }

    /*
     * Equals and HashCode override in Employee basic class
     * */
}
