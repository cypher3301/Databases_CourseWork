package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.WorkShiftType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    @NotNull
    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    protected WorkShiftType type;

    @NotNull
    @Column(name = "time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;



    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "work_shifts_operator"))
    private Operator operator;


    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "work_shifts_station"))
    private Station station;


    @OneToMany
    private Collection<Invoice> invoices;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkShift)) return false;

        WorkShift workShift = (WorkShift) o;

        if (getType() != workShift.getType()) return false;
        if (getTime() != null ? !getTime().equals(workShift.getTime()) : workShift.getTime() != null) return false;
        if (getOperator() != null ? !getOperator().equals(workShift.getOperator()) : workShift.getOperator() != null)
            return false;
        if (getStation() != null ? !getStation().equals(workShift.getStation()) : workShift.getStation() != null)
            return false;
        return getInvoices().equals(workShift.getInvoices());
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        result = 31 * result + (getStation() != null ? getStation().hashCode() : 0);
        result = 31 * result + getInvoices().hashCode();
        return result;
    }

}

