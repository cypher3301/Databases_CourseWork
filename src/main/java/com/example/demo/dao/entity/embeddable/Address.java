package com.example.demo.dao.entity.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Address {

//    @Column(name = "region", nullable = false, length = 24)
    private String region;
//    @Column(name = "city",   nullable = false, length = 24)
    private String city;
//    @Column(name = "street",                   length = 24)
    protected String street;

//    @Column(name = "building", nullable = false, length = 24)
    private String building;
//    @Column(name = "campus",   nullable = false, length = 24)
    private String campus;
//    @Column(name = "apartment",                   length = 24)
    private String apartment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getRegion() != null ? !getRegion().equals(address.getRegion()) : address.getRegion() != null) return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getBuilding() != null ? !getBuilding().equals(address.getBuilding()) : address.getBuilding() != null)
            return false;
        if (!getCampus().equals(address.getCampus())) return false;
        return getApartment().equals(address.getApartment());
    }

    @Override
    public int hashCode() {
        int result = getRegion() != null ? getRegion().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getBuilding() != null ? getBuilding().hashCode() : 0);
        result = 31 * result + getCampus().hashCode();
        result = 31 * result + getApartment().hashCode();
        return result;
    }
}
