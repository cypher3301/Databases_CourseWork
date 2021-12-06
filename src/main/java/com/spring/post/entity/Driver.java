package com.spring.post.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Driver {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;


    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_employee"))
    @NotNull(message = "Driver car cannot be null")
    private Employee employee;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_number", nullable = false, referencedColumnName = "car_number",
            foreignKey = @ForeignKey(name = "fk_driver_car"))
    @NotNull(message = "Driver car cannot be null")
    private Car car;

    @OneToMany(mappedBy = "driver")
    @Size(message = "Driver waybills must be greater or equal 0")
    @NotNull(message = "Driver waybills cannot be null")
    private Collection<Waybill> waybills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Collection<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(Collection<Waybill> waybills) {
        this.waybills = waybills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (getId() != driver.getId()) return false;
        if (employee != null ? !employee.equals(driver.employee) : driver.employee != null) return false;
        if (getCar() != null ? !getCar().equals(driver.getCar()) : driver.getCar() != null) return false;
        return getWaybills() != null ? getWaybills().equals(driver.getWaybills()) : driver.getWaybills() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (getCar() != null ? getCar().hashCode() : 0);
        result = 31 * result + (getWaybills() != null ? getWaybills().hashCode() : 0);
        return result;
    }
}
