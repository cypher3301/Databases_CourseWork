package com.spring.post.entity;

import com.spring.post.entity.ancestor.Employee;
import com.spring.post.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity(name = "driver")
@Table(name = "driver",  schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "uk_driver_car_number", columnNames = "car_number"),
        @UniqueConstraint(name = "uk_driver_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_driver_identification_code", columnNames = "identification_code"),
        @UniqueConstraint(name = "uk_driver_price_card_number", columnNames = "price_card_number")
})
@NoArgsConstructor
@Getter
@Setter
public class Driver extends Employee {


    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_number", nullable = false, referencedColumnName = "car_number",
            foreignKey = @ForeignKey(name = "driver_car"))
    @NotNull(message = "Driver car cannot be null")
    private Car car;

    @OneToMany(mappedBy = "driver")
    @Size(message = "Driver waybills must be greater or equal 0")
    @NotNull(message = "Driver waybills cannot be null")
    private Collection<Waybill> waybills;

    public Driver(Long id, String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Car car) {
        super(id, firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.car = car;
    }

    public Driver(String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position, Car car) {
        super(firstname, patronymic, surname, phone, email, priceCardNumber, address, identificationCode, position);
        this.car = car;
    }


    /*
     * Equals and HashCode override in Employee basic class
     * */


    @Override
    public String toString() {
        return "Driver{" +
                "car=" + car +
                ", waybills=" + waybills +
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
