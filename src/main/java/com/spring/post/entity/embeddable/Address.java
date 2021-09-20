package com.spring.post.entity.embeddable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    protected String region;
    protected String city;
    protected String street;

    protected String building;
    protected String campus;
    protected String apartment;


    public Address(String region, String city, String street, String building, String campus, String apartment) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.campus = campus;
        this.apartment = apartment;
    }

    public Address(String region, String city, String building) {
        this.region = region;
        this.city = city;
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getRegion() != null ? !getRegion().equals(address.getRegion()) : address.getRegion() != null) return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (!getStreet().equals(address.getStreet())) return false;
        if (getBuilding() != null ? !getBuilding().equals(address.getBuilding()) : address.getBuilding() != null)
            return false;
        if (!getCampus().equals(address.getCampus())) return false;
        return getApartment().equals(address.getApartment());
    }

    @Override
    public int hashCode() {
        int result = getRegion() != null ? getRegion().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + (getBuilding() != null ? getBuilding().hashCode() : 0);
        result = 31 * result + getCampus().hashCode();
        result = 31 * result + getApartment().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", campus='" + campus + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
