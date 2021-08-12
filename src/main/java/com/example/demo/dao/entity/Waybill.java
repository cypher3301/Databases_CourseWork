package com.example.demo.dao.entity;

import com.example.demo.dao.entity.status.WaybillType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "waybill")
@Table(name = "waybill", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Waybill {

    private static final int invoicesQuantity = 16192;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "datetime", nullable = false)
    @NotNull(message = "Waybill datetime cannot be null")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;

    @Column(name = "type", nullable = false, length = 12)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Waybill type cannot be null")
    private WaybillType type;

    @Column(name = "quantity", nullable = false, length = invoicesQuantity)
    @PositiveOrZero(message = "Waybill quantity cannot be less 0")
    @Digits(message = "Waybill from 0 to 5 digits before dot", integer = 5, fraction = 0)
    @Min(value = 0, message = "Waybill quantity minimum 0")
    @Max(value = invoicesQuantity, message = "Waybill quantity minimum invoicesQuantity")
    private int quantity;


    @OneToMany
    @Size(message = "Waybill can have 0 items", max = invoicesQuantity)
    @NotNull(message = "Waybill cannot be null")
    @JoinTable(name = "waybill_invoices",
            joinColumns = {
                    @JoinColumn(name = "waybill_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_waybill_invoices_waybill_id"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "invoices_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_waybill_invoices_invoices_id"))},
            uniqueConstraints = @UniqueConstraint(name = "uk_waybill_invoices_id", columnNames = "invoices_id"))
    private Collection<Invoice> invoices;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "waybills_operator"))
    @NotNull(message = "Waybill operator cannot be null")
    private Operator operator;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "driver_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "waybills_driver"))
    @NotNull(message = "Waybill driver cannot be null")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "station_sender_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "waybills_station_sender"))
    @NotNull(message = "Waybill station sender cannot be null")
    private Station stationSender;

    @ManyToOne
    @JoinColumn(name = "station_recipient_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "waybills_station_recipient"))
    @NotNull(message = "Waybill station recipient cannot be null")
    private Station stationRecipient;


    public Waybill(long id, WaybillType type, Operator operator, Driver driver, Station stationSender, Station stationRecipient) {
        this.id = id;
        this.type = type;
        this.operator = operator;
        this.driver = driver;
        this.stationSender = stationSender;
        this.stationRecipient = stationRecipient;
    }

    public Waybill(WaybillType type, Operator operator, Driver driver, Station stationSender, Station stationRecipient) {
        this.type = type;
        this.operator = operator;
        this.driver = driver;
        this.stationSender = stationSender;
        this.stationRecipient = stationRecipient;
    }

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
}

