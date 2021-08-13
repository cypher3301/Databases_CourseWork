package com.example.demo.dao.entity.ancestor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

import static com.example.demo.dao.entity.ancestor.Util.regExpPhone;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Person implements BaseEntity{

    private static final int minValuePersonName = 2;
    private static final int maxValuePersonName = 20;
    private static final int lengthPhone = 16;

    private static final String className = "Person ";
    private static final String columnId = "id";
    private static final String columnFirstname = "firstname";
    private static final String columnPatronymic = "patronymic";
    private static final String columnSurname = "surname";
    private static final String columnPhone = "phone";
    private static final boolean nullableFalse = false;

    private static final String messageIsIllegalOrEmpty = " is illegal or empty ";
    private static final String messageMustBeMoreThan = " must be more than ";
    private static final String messageMustBeLessThan = " must be more than ";
    private static final String messageCharacters = " characters ";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO /*, generator = "IdOrGenerated"*/)
//    @GenericGenerator(name = "IdOrGenerated", strategy = "com.example.demo.dao.entity.generators.UseIdOrGenerate")
    @Column(name = columnId)
    protected Long id;

    @Column(name = columnFirstname, nullable = nullableFalse, length = maxValuePersonName)
    @NotBlank(message = className + columnFirstname + messageIsIllegalOrEmpty)
    @Min(value = minValuePersonName, message = className + columnFirstname + messageMustBeMoreThan + minValuePersonName + messageCharacters)
    @Max(value = maxValuePersonName, message = className + columnFirstname + messageMustBeLessThan + maxValuePersonName + messageCharacters)
    protected String firstname;

    @Column(name = columnPatronymic, nullable = nullableFalse, length = maxValuePersonName)
    @NotBlank(message = className + columnPatronymic + messageIsIllegalOrEmpty)
    @Min(value = minValuePersonName, message = className + columnPatronymic + messageMustBeMoreThan + minValuePersonName + messageCharacters)
    @Max(value = maxValuePersonName, message = className + columnPatronymic + messageMustBeLessThan + maxValuePersonName + messageCharacters)
    protected String patronymic;

    @Column(name = columnSurname, nullable = nullableFalse, length = maxValuePersonName)
    @NotBlank(message = className + columnSurname + messageIsIllegalOrEmpty)
    @Min(value = minValuePersonName, message = className + columnSurname + messageMustBeMoreThan + minValuePersonName + messageCharacters)
    @Max(value = maxValuePersonName, message = className + columnSurname + messageMustBeLessThan + maxValuePersonName + messageCharacters)
    protected String surname;

    @Column(name = columnPhone, nullable = nullableFalse, length = lengthPhone)
    @NotBlank(message = className + columnPhone + messageIsIllegalOrEmpty)
    @Digits(message = className + columnPhone + " illegal", integer = lengthPhone, fraction = 0)
    @Pattern(message = className + columnPhone + " number is illegal", regexp = regExpPhone)
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
