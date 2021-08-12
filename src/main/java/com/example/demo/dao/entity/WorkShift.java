package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.WorkShiftType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;


@Entity(name = "work_shift")
@Table(name = "work_shift", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "WorkShift type cannot be null")
    protected WorkShiftType type;

    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "WorkShift datetime must be past or current time")
    @NotNull(message = "WorkShift datetime cannot be null")
    protected Date datetime;


    @ManyToOne(optional = false)
    @JoinColumn(name = "operator_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "work_shifts_operator"))
    @NotNull(message = "WorkShift operator cannot be null")
    private Operator operator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "work_shifts_station"))
    @NotNull(message = "WorkShift station cannot be null")
    private Station station;

    @OneToMany
    @Size(message = "WorkShift invoices must be greater or equal 0")
    @NotNull(message = "WorkShift invoices cannot be null")
    @JoinTable(name = "work_shift_invoices",
            joinColumns = {
                    @JoinColumn(name = "work_shift_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_work_shift_invoices_work_shift_id"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "invoices_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_work_shift_invoices_work_invoices_id"))},
            uniqueConstraints = @UniqueConstraint(name = "uk_work_shift_invoices_id", columnNames = "invoices_id"))
    private Collection<Invoice> invoices;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkShift)) return false;

        WorkShift workShift = (WorkShift) o;

        if (getType() != workShift.getType()) return false;
        if (getDatetime() != null ? !getDatetime().equals(workShift.getDatetime()) : workShift.getDatetime() != null) return false;
        if (getOperator() != null ? !getOperator().equals(workShift.getOperator()) : workShift.getOperator() != null)
            return false;
        if (getStation() != null ? !getStation().equals(workShift.getStation()) : workShift.getStation() != null)
            return false;
        return getInvoices().equals(workShift.getInvoices());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        result = 31 * result + (getStation() != null ? getStation().hashCode() : 0);
        result = 31 * result + getInvoices().hashCode();
        return result;
    }

}

