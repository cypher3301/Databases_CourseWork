package com.example.demo.dao.entity.ancestor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @Column(name = "firstname",nullable = false, length = 20)
    @NotBlank(       message = "Person name is illegal or empty")
    @Min(value = 2,  message = "Name must be more than 2 characters")
    @Max(value = 20, message = "Name must be less than 20 characters")
    protected String firstname;

    @Column(name = "patronymic",nullable = false, length = 20)
    @NotBlank(       message = "Person patronymic is illegal or empty")
    @Min(value = 2,  message = "Patronymic must be more than 2 characters")
    @Max(value = 20, message = "Patronymic must be less than 20 characters")
    protected String patronymic;

    @Column(name = "surname",nullable = false, length = 20)
    @NotBlank(       message = "Person surname is illegal or empty")
    @Min(value = 2,  message = "Surname must be more than 2 characters")
    @Max(value = 20, message = "Surname must be less than 20 characters")
    protected String surname;

    @Column(name = "phone",nullable = false, length = 16)
    @Digits(   message = "Person illegal phone", integer = 16, fraction = 0)
    @NotBlank( message = "Person phone is illegal or empty")
    @Pattern(  message = "Person phon number is illegal",
            regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    protected String phone;


    public Person(Long id, String firstname, String patronymic, String surname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.surname = surname;
        this.phone = phone;
    }

    public Person(String firstname, String patronymic, String surname, String phone) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getFirstname() != null ? !getFirstname().equals(person.getFirstname()) : person.getFirstname() != null)
            return false;
        if (!getPatronymic().equals(person.getPatronymic())) return false;
        if (getSurname() != null ? !getSurname().equals(person.getSurname()) : person.getSurname() != null)
            return false;
        return getPhone() != null ? getPhone().equals(person.getPhone()) : person.getPhone() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstname() != null ? getFirstname().hashCode() : 0;
        result = 31 * result + getPatronymic().hashCode();
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        return result;
    }

}
