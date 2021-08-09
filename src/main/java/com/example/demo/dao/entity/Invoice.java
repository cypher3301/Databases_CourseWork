package com.example.demo.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;


@Entity(name = "invoice")
@Table(name = "invoice", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;


    @NotNull
    @Column(name = "quantity",  nullable = false)
    protected double quantity;


    @NotNull
    @Column(name = "type",  nullable = false)
    protected String type;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "datetime", nullable = false)
    private Date datetime;



    @OneToOne
    @JoinColumn(name = "station_recipient_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "invoice_station"))
    private Station stationRecipient;


    @OneToMany(mappedBy = "invoice")
    private Collection<Package> packages;

    @OneToMany(mappedBy = "invoice")
    private Collection<InvoiceTimeline> timeline;



    @ManyToOne
    @JoinColumn(name = "operator_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "invoices_operator"))
    private Operator operator;

    @ManyToOne
    @JoinColumn(name = "sender_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "invoices_client_sender"))
    private Client clientSender;

    @ManyToOne
    @JoinColumn(name = "recipient_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "invoices_client_recipient"))
    private Client clientRecipient;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (Double.compare(invoice.getQuantity(), getQuantity()) != 0) return false;
        if (getType() != null ? !getType().equals(invoice.getType()) : invoice.getType() != null) return false;
        if (getDatetime() != null ? !getDatetime().equals(invoice.getDatetime()) : invoice.getDatetime() != null)
            return false;
        if (getStationRecipient() != null ? !getStationRecipient().equals(invoice.getStationRecipient()) : invoice.getStationRecipient() != null)
            return false;
        if (getPackages() != null ? !getPackages().equals(invoice.getPackages()) : invoice.getPackages() != null)
            return false;
        if (getOperator() != null ? !getOperator().equals(invoice.getOperator()) : invoice.getOperator() != null)
            return false;
        if (getClientSender() != null ? !getClientSender().equals(invoice.getClientSender()) : invoice.getClientSender() != null)
            return false;
        return getClientRecipient() != null ? getClientRecipient().equals(invoice.getClientRecipient()) : invoice.getClientRecipient() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getQuantity());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        result = 31 * result + (getStationRecipient() != null ? getStationRecipient().hashCode() : 0);
        result = 31 * result + (getPackages() != null ? getPackages().hashCode() : 0);
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        result = 31 * result + (getClientSender() != null ? getClientSender().hashCode() : 0);
        result = 31 * result + (getClientRecipient() != null ? getClientRecipient().hashCode() : 0);
        return result;
    }
}
