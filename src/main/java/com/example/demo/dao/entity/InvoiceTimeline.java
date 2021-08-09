package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.TimelineStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice_timeline", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class InvoiceTimeline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetime")
    private Date datetime;

    @Enumerated(EnumType.STRING)
    private TimelineStatus status;

    @ManyToOne
    @JoinColumn(name = "invoice_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "timeline_invoice"))
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "actual_station_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "timeline_station"))
    private Station actualStation;

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
