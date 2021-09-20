package com.spring.post.entity.ancestor;

import com.spring.post.entity.Position;
import com.spring.post.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

import static com.spring.post.entity.ancestor.Util.regExpEmail;


@Entity(name = "employee")
@Table(name = "employee", catalog = "postOffice", schema = "public", indexes = {
        @Index(name = "_city", columnList = "city"),
        @Index(name = "_phone", columnList = "phone")
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public abstract class Employee extends Person {

    private static final boolean nullableFalse = false;
    private static final boolean uniqueTrue = true;


    @Column(name = "email", nullable = nullableFalse)
    @NotBlank(message = "Employee email is illegal or empty")
    @Min(value = 5, message = "Email is too less")
    @Email(message = "Email is not valid",
            regexp = regExpEmail)
    protected String email;

    @Column(name = "price_card_number", nullable = nullableFalse, length = 16)
    @Digits(message = "Price card number only digits", integer = 16, fraction = 0)
    @NotBlank(message = "Employee  price card number is illegal or empty")
    protected String priceCardNumber;

    @NotNull(message = "Address cannot be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "region", column = @Column(name = "region", length = 24, nullable = nullableFalse)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 24, nullable = nullableFalse)),
            @AttributeOverride(name = "street", column = @Column(name = "street", length = 24)),
            @AttributeOverride(name = "building", column = @Column(name = "building", length = 4, nullable = nullableFalse)),
            @AttributeOverride(name = "campus", column = @Column(name = "campus", length = 4)),
            @AttributeOverride(name = "apartment", column = @Column(name = "apartment", length = 4)),
    })
    protected Address address;

    @Column(name = "identification_code", nullable = nullableFalse, length = 10)
    @NotBlank(message = "Employee identification code is is illegal or empty")
    @Digits(message = "Identification code only digits", integer = 10, fraction = 0)
    @Min(10)
    protected String identificationCode;

    //date of birthday

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id", referencedColumnName = "name", foreignKey = @ForeignKey(name = "employee_position"))
    @NotNull(message = "Employee position cannot be null")
    protected Position position;


    public Employee(Long id, String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position) {
        super(id, firstname, patronymic, surname, phone);
        this.email = email;
        this.priceCardNumber = priceCardNumber;
        this.address = address;
        this.identificationCode = identificationCode;
        this.position = position;
    }

    public Employee(String firstname, String patronymic, String surname, String phone, String email, String priceCardNumber, Address address, String identificationCode, Position position) {
        super(firstname, patronymic, surname, phone);
        this.email = email;
        this.priceCardNumber = priceCardNumber;
        this.address = address;
        this.identificationCode = identificationCode;
        this.position = position;
    }


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
