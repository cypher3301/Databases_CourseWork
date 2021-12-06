package com.spring.post.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "firstname", nullable = false, length = 24)
    private String firstname;
    @Basic
    @Column(name = "patronymic", nullable = false, length = 24)
    private String patronymic;
    @Basic
    @Column(name = "phone", nullable = false, length = 24)
    private String phone;
    @Basic
    @Column(name = "surname", nullable = false, length = 24)
    private String surname;
    @Basic
    @Column(name = "apartment", nullable = true, length = 4)
    private String apartment;
    @Basic
    @Column(name = "building", nullable = false, length = 4)
    private String building;
    @Basic
    @Column(name = "campus", nullable = true, length = 4)
    private String campus;
    @Basic
    @Column(name = "city", nullable = false, length = 24)
    private String city;
    @Basic
    @Column(name = "region", nullable = false, length = 24)
    private String region;
    @Basic
    @Column(name = "street", nullable = true, length = 24)
    private String street;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "identification_code", nullable = false, length = 12)
    private String identificationCode;
    @Basic
    @Column(name = "price_card_number", nullable = false, length = 20)
    private String priceCardNumber;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;
    @OneToMany(mappedBy = "employee")
    private Collection<Operator> operators;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getPriceCardNumber() {
        return priceCardNumber;
    }

    public void setPriceCardNumber(String priceCardNumber) {
        this.priceCardNumber = priceCardNumber;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position positionByPositionId) {
        this.position = positionByPositionId;
    }

    public Collection<Operator> getOperators() {
        return operators;
    }

    public void setOperators(Collection<Operator> operatorsById) {
        this.operators = operatorsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != employee.getId()) return false;
        if (getFirstname() != null ? !getFirstname().equals(employee.getFirstname()) : employee.getFirstname() != null)
            return false;
        if (getPatronymic() != null ? !getPatronymic().equals(employee.getPatronymic()) : employee.getPatronymic() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(employee.getPhone()) : employee.getPhone() != null) return false;
        if (getSurname() != null ? !getSurname().equals(employee.getSurname()) : employee.getSurname() != null)
            return false;
        if (getApartment() != null ? !getApartment().equals(employee.getApartment()) : employee.getApartment() != null)
            return false;
        if (getBuilding() != null ? !getBuilding().equals(employee.getBuilding()) : employee.getBuilding() != null)
            return false;
        if (getCampus() != null ? !getCampus().equals(employee.getCampus()) : employee.getCampus() != null)
            return false;
        if (getCity() != null ? !getCity().equals(employee.getCity()) : employee.getCity() != null) return false;
        if (getRegion() != null ? !getRegion().equals(employee.getRegion()) : employee.getRegion() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(employee.getStreet()) : employee.getStreet() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(employee.getEmail()) : employee.getEmail() != null) return false;
        if (getIdentificationCode() != null ? !getIdentificationCode().equals(employee.getIdentificationCode()) : employee.getIdentificationCode() != null)
            return false;
        if (getPriceCardNumber() != null ? !getPriceCardNumber().equals(employee.getPriceCardNumber()) : employee.getPriceCardNumber() != null)
            return false;
        if (getPosition() != null ? !getPosition().equals(employee.getPosition()) : employee.getPosition() != null)
            return false;
        return getOperators() != null ? getOperators().equals(employee.getOperators()) : employee.getOperators() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getFirstname() != null ? getFirstname().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getApartment() != null ? getApartment().hashCode() : 0);
        result = 31 * result + (getBuilding() != null ? getBuilding().hashCode() : 0);
        result = 31 * result + (getCampus() != null ? getCampus().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getIdentificationCode() != null ? getIdentificationCode().hashCode() : 0);
        result = 31 * result + (getPriceCardNumber() != null ? getPriceCardNumber().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getOperators() != null ? getOperators().hashCode() : 0);
        return result;
    }
}
