package com.spring.post.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Operator {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;


    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @OneToMany(mappedBy = "operator")
    private Collection<Invoice> invoices;

    @OneToMany(mappedBy = "operator")
    private Collection<Waybill> waybills;

    @OneToMany(mappedBy = "operator")
    private Collection<WorkShift> workShifts;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "operators_station"))
    @NotNull(message = "Operator station cannot be null")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station stationByStationId) {
        this.station = stationByStationId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeByEmployeeId) {
        this.employee = employeeByEmployeeId;
    }

    public Collection<Invoice> getInvoices() {
        if (invoices==null) return new ArrayList<>();
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Collection<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(Collection<Waybill> waybills) {
        this.waybills = waybills;
    }

    public Collection<WorkShift> getWorkShifts() {
        return workShifts;
    }

    public void setWorkShifts(Collection<WorkShift> workShifts) {
        this.workShifts = workShifts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;

        Operator operator = (Operator) o;

        if (getLogin() != null ? !getLogin().equals(operator.getLogin()) : operator.getLogin() != null) return false;
        if (getStation() != null ? !getStation().equals(operator.getStation()) : operator.getStation() != null)
            return false;
        return getEmployee() != null ? getEmployee().equals(operator.getEmployee()) : operator.getEmployee() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getStation() != null ? getStation().hashCode() : 0);
        result = 31 * result + (getEmployee() != null ? getEmployee().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", login='" + login + '\'' +
//                ", invoices=" + invoices +
//                ", waybills=" + waybills +
//                ", workShifts=" + workShifts +
//                ", station=" + station +
//                ", employeeByEmployeeId=" + employee +
                '}';
    }
}
