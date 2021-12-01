package com.spring.post.entity;

import com.spring.post.entity.embeddable.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "station")
@Table(name = "station", schema = "public", 
        indexes = @Index(name = "city", columnList = "city"),
        uniqueConstraints = @UniqueConstraint(name = "region_city_street_building",
                columnNames = {
                        "region", "city", "street", "building"
                })
)
@NoArgsConstructor
@Getter
@Setter
public class Station  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Embedded
    @NotNull(message = "Station address cannot be null")
    @AttributeOverrides({
            @AttributeOverride(name = "region",    column = @Column(name = "region",    length = 24, nullable = false)),
            @AttributeOverride(name = "city",      column = @Column(name = "city",      length = 24, nullable = false)),
            @AttributeOverride(name = "street",    column = @Column(name = "street",    length = 24                  )),
            @AttributeOverride(name = "building",  column = @Column(name = "building",  length = 4,  nullable = false)),
            @AttributeOverride(name = "campus",    column = @Column(name = "campus",    length = 4                   )),
            @AttributeOverride(name = "apartment", column = @Column(name = "apartment", length = 4                   )),
    })    private Address address;

    @Column(name = "number", nullable = false)
    private short number;


    @OneToMany(mappedBy = "station")
    private Collection<Operator> employees;

    @OneToMany(mappedBy = "station")
    private Collection<WorkShift> workShifts;

    @OneToMany(mappedBy = "stationRecipient")
    private Collection<Invoice> invoices;


    public Station(long id, Address address, short number) {
        this.id = id;
        this.address = address;
        this.number = number;
    }

    public Station(Address address, short number) {
        this.address = address;
        this.number = number;
    }


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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", address=" + address +
                ", number=" + number +
                ", employees=" + employees +
                ", workShifts=" + workShifts +
                ", invoices=" + invoices +
                '}';
    }
}
