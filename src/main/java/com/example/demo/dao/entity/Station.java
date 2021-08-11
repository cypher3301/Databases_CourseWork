package com.example.demo.dao.entity;

import com.example.demo.dao.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "station")
@Table(name = "station", schema = "public", catalog = "postOffice")
@NoArgsConstructor
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Embedded
    @NotNull(message = "Station address cannot be null")
    private Address address;

    @Column(name = "number", nullable = false)
    private short number;


    @OneToMany(mappedBy = "station")
    private Collection<Operator> employees;

    @OneToMany(mappedBy = "station")
    private Collection<WorkShift> workShifts;

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;

        Station station = (Station) o;

        if (getNumber() != station.getNumber()) return false;
        return getAddress() != null ? getAddress().equals(station.getAddress()) : station.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (int) getNumber();
        return result;
    }
}
