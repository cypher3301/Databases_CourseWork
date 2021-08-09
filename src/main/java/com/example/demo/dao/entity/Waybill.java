package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.WaybillType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;

@Entity(name = "waybill")
@Table(name = "waybill", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Waybill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @NotNull
    @Column(name = "datetime", nullable = false)
    protected Timestamp dateAndTime;

    @Column(name = "type")
    protected WaybillType type;

    @Column(name = "quantity")
    @Min(1)
    private int quantity;


    @OneToMany
    private Collection<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "waybills_operator"))
    private Operator operator;


    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "waybills_driver"))
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "station_sender_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "waybills_station_sender"))
    private Station stationSender;


    @ManyToOne
    @JoinColumn(name = "station_recipient_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "waybills_station_recipient"))
    private Station stationRecipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Waybill)) return false;

        Waybill waybill = (Waybill) o;

        if (getQuantity() != waybill.getQuantity()) return false;
        if (getDateAndTime() != null ? !getDateAndTime().equals(waybill.getDateAndTime()) : waybill.getDateAndTime() != null)
            return false;
        if (getType() != waybill.getType()) return false;
        if (!getInvoices().equals(waybill.getInvoices())) return false;
        if (getOperator() != null ? !getOperator().equals(waybill.getOperator()) : waybill.getOperator() != null)
            return false;
        if (getDriver() != null ? !getDriver().equals(waybill.getDriver()) : waybill.getDriver() != null) return false;
        if (getStationSender() != null ? !getStationSender().equals(waybill.getStationSender()) : waybill.getStationSender() != null)
            return false;
        return getStationRecipient() != null ? getStationRecipient().equals(waybill.getStationRecipient()) : waybill.getStationRecipient() == null;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + getQuantity();
        result = 31 * result + getInvoices().hashCode();
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (getStationSender() != null ? getStationSender().hashCode() : 0);
        result = 31 * result + (getStationRecipient() != null ? getStationRecipient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Waybill{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", type=" + type +
                ", quantity=" + quantity +
                ", invoices=" + invoices +
                ", operator=" + operator +
                ", driver=" + driver +
                ", stationSender=" + stationSender +
                ", stationRecipient=" + stationRecipient +
                '}';
    }
}

