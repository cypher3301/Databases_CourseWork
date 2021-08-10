package com.example.demo.dao.entity.ancestor;

import com.example.demo.dao.entity.Position;
import com.example.demo.dao.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Table(name = "Employee")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public abstract class Employee extends Person {


    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(      message = "Employee email is illegal or empty")
    @Min(value = 5, message = "Email is too less")
    @Email(         message = "Email is not valid",
            regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    protected String email;

    @Column(name = "price_card_number", nullable = false, unique = true, length = 16)
    @Digits(    message = "Price card number only digits", integer = 16, fraction = 0)
    @NotBlank(  message = "Employee  price card number is illegal or empty")
    protected String priceCardNumber;

    @NotNull(message = "Address cannot be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "region",    column = @Column(name = "region",    length = 24, nullable = false)),
            @AttributeOverride(name = "city",      column = @Column(name = "city",      length = 24, nullable = false)),
            @AttributeOverride(name = "street",    column = @Column(name = "street",    length = 24                  )),
            @AttributeOverride(name = "building",  column = @Column(name = "building",  length = 4,  nullable = false)),
            @AttributeOverride(name = "campus",    column = @Column(name = "campus",    length = 4                   )),
            @AttributeOverride(name = "apartment", column = @Column(name = "apartment", length = 4                   )),
    })
    protected Address address;

    @NotBlank(  message = "Employee identification code is is illegal or empty")
    @Digits(    message = "Identification code only digits", integer = 10, fraction = 0)
    @Column(name = "identification_code", nullable = false, unique = true, length = 10)
    @Min(10)
    protected String identificationCode;


    @NotNull(message = "Employee position cannot be null")
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "name", foreignKey = @ForeignKey(name = "employee_position"))
    protected Position position;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return getIdentificationCode() != null
                ? getIdentificationCode().equals(employee.getIdentificationCode())
                : employee.getIdentificationCode() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getIdentificationCode() != null ? getIdentificationCode().hashCode() : 0);
        return result;
    }

}
