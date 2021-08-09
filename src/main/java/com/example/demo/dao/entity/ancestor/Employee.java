package com.example.demo.dao.entity.ancestor;

import com.example.demo.dao.entity.Position;
import com.example.demo.dao.entity.embeddable.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode(callSuper = true)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public abstract class Employee extends Person{


    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "price_card_number", length = 16)
    protected long priceCardNumber;

    private Address address;

    private String identificationCode;


    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "name",foreignKey = @ForeignKey(name = "employee_position"))
    private Position position;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        return getIdentificationCode() != null ? getIdentificationCode().equals(employee.getIdentificationCode()) : employee.getIdentificationCode() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getIdentificationCode() != null ? getIdentificationCode().hashCode() : 0);
        return result;
    }

}
