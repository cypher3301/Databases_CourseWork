package com.spring.post.entity;

import com.spring.post.entity.status.WorkShiftType;
import lombok.Builder;
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
@Table(name = "work_shift",  schema = "public")
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "WorkShift type cannot be null")
    private WorkShiftType type;

    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "WorkShift datetime must be past or current time")
    @NotNull(message = "WorkShift datetime cannot be null")
    private Date datetime;


    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "work_shifts_operator"))
    @NotNull(message = "WorkShift operator cannot be null")
    private Operator operator;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
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

    public WorkShift(WorkShiftType type, Date datetime, Operator operator, Station station, Collection<Invoice> invoices) {
        this.type = type;
        this.datetime = datetime;
        this.operator = operator;
        this.station = station;
        this.invoices = invoices;
    }

    public WorkShift(long id, WorkShiftType type, Date datetime, Operator operator, Station station, Collection<Invoice> invoices) {
        this.id = id;
        this.type = type;
        this.datetime = datetime;
        this.operator = operator;
        this.station = station;
        this.invoices = invoices;
    }

    public WorkShift(long id, WorkShiftType type, Operator operator, Station station) {
        this.id = id;
        this.type = type;
        this.operator = operator;
        this.station = station;
    }

    public WorkShift(WorkShiftType type, Operator operator, Station station) {
        this.type = type;
        this.operator = operator;
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkShift)) return false;

        WorkShift workShift = (WorkShift) o;

        if (getType() != workShift.getType()) return false;
        if (!getDatetime().equals(workShift.getDatetime())) return false;
        if (!getOperator().equals(workShift.getOperator())) return false;
        if (!getStation().equals(workShift.getStation())) return false;
        return getInvoices() != null ? getInvoices().equals(workShift.getInvoices()) : workShift.getInvoices() == null;
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getDatetime().hashCode();
        result = 31 * result + getOperator().hashCode();
        result = 31 * result + getStation().hashCode();
        result = 31 * result + (getInvoices() != null ? getInvoices().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkShift{" +
                "id=" + id +
                ", type=" + type +
                ", datetime=" + datetime +
                ", operator=" + operator +
                ", station=" + station +
                ", invoices=" + invoices +
                '}';
    }
}

