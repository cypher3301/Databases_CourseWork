package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.TimelineStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Table(name = "invoice_timeline", catalog = "postOffice", schema = "public", indexes = {
        @Index(name = "invoice_timeline_status", columnList = "status"),
        @Index(name = "invoice_timeline_datetime", columnList = "datetime"),
})
@NoArgsConstructor
@Getter
@Setter
public class InvoiceTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "Invoice timeline only present time")
    @NotNull(message = "Invoice datetime cannot be null")
    private Date datetime;

    @Column(name = "status", nullable = false, length = 24)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Invoice timeline status cannot be null")
    private TimelineStatus status;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "timeline_invoice"))
    @NotNull(message = "Invoice timeline cannot be null invoice")
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "actual_station_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "timeline_station"))
    @NotNull(message = "Invoice timeline cannot have null actual station")
    private Station actualStation;


    public InvoiceTimeline(long id, TimelineStatus status, Station actualStation) {
        this.id = id;
        this.status = status;
        this.actualStation = actualStation;
    }

    public InvoiceTimeline(TimelineStatus status, Station actualStation) {
        this.status = status;
        this.actualStation = actualStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceTimeline)) return false;

        InvoiceTimeline that = (InvoiceTimeline) o;

        if (getDatetime() != null ? !getDatetime().equals(that.getDatetime()) : that.getDatetime() != null)
            return false;
        if (getStatus() != that.getStatus()) return false;
        if (getInvoice() != null ? !getInvoice().equals(that.getInvoice()) : that.getInvoice() != null) return false;
        return getActualStation() != null ? getActualStation().equals(that.getActualStation()) : that.getActualStation() == null;
    }

    @Override
    public int hashCode() {
        int result = getDatetime() != null ? getDatetime().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getInvoice() != null ? getInvoice().hashCode() : 0);
        result = 31 * result + (getActualStation() != null ? getActualStation().hashCode() : 0);
        return result;
    }
}
