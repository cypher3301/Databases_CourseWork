package com.example.demo.dao.entity.ancestor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"id","patronymic"})
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "firstname",nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "patronymic",nullable = false)
    private String patronymic;

    @NotNull
    @Column(name = "surname",nullable = false)
    private String surname;

    @NotNull
    @Column(name = "phone",nullable = false)
    private String phone;


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
