package com.spring.post.entity;

import com.spring.post.convertor.PackageTypeArrayOfEnumToStringConvertor;
import com.spring.post.entity.status.InvoiceType;
import com.spring.post.entity.status.PackageType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


@Entity(name = "invoice")
@Table(name = "invoice",  schema = "public", indexes = {
        @Index(name = "invoice_type", columnList = "type"),
        @Index(name = "invoice_datetime", columnList = "datetime")
})
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "quantity",  nullable = false)
    @ColumnDefault( value = "1")
    @DecimalMin( message = "Invoice quantity is less than required",  value = "1")
    @DecimalMax( message = "Invoice quantity is more than necessary", value = "255")
    @Digits(     message = "Invoice quantity can be up to 255", integer = 3, fraction = 0 )
    @Positive(   message = "Invoice must have quantity greater than or equal 1")
    private int quantity;

    @Column(name = "delivery_type",     nullable = false, length = 128)
    @NotNull(message = "Invoice type cannot be null")
    private String deliveryType;

    //    @Type(type = "com.example.demo.dao.convertor.InvoiceTypeArrayOfEnumToStringConvertor")
    @Convert(converter = PackageTypeArrayOfEnumToStringConvertor.class)
    @Column(name = "type", nullable = false, length = 16)
//    @NotBlank(message = "Package type is null or empty")
    private PackageType[] type;

    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(       message = "Invoice datetime cannot be null")
//    @PastOrPresent( message = "Invoice datetime must be past or current time")
    private Date datetime;


    @OneToMany(mappedBy = "invoice")
    @Size(    message = "Invoice can have from 1 to 255 packages", min = 1, max = 255)
    @NotNull( message = "Invoice must have greater than or equal 1 package")
    private Collection<Package> packages;

    @OneToMany(mappedBy = "invoice")
    @Size(    message = "Invoice datetime must be greater than 0", min = 1, max = 60)
//    @NotNull( message = "Invoice timeline cannot be null")
    private Collection<InvoiceTimeline> timeline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "operator_id",  nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoices_operator"))
//    @NotNull(message = "Invoice operator cannot be null")
    private Operator operator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id",    nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoices_client_sender"))
    @NotNull(message = "Invoice client sender cannot be null")
    private Client clientSender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoices_client_recipient"))
    @NotNull(message = "Invoice client recipient cannot be null")
    private Client clientRecipient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "station_recipient_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "invoice_station"))
    @NotNull(message = "Invoice recipient station cannot be null")
    private Station stationRecipient;


    public Invoice(long id, int quantity, @NotBlank(message = "Package type is null or empty") PackageType[] type,
                   Operator operator, Client clientSender, Client clientRecipient, Station stationRecipient) {
        this.id = id;
        this.quantity = quantity;
        this.type = type;
        this.operator = operator;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
        this.stationRecipient = stationRecipient;
    }

    public Invoice(int quantity, @NotBlank(message = "Package type is null or empty") PackageType[] type,
                   Operator operator, Client clientSender, Client clientRecipient, Station stationRecipient) {
        this.quantity = quantity;
        this.type = type;
        this.operator = operator;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
        this.stationRecipient = stationRecipient;
    }

    public Invoice(long id, int quantity, String deliveryType, @NotBlank(message = "Package type is null or empty") PackageType[] type, Date datetime, Collection<Package> packages, Collection<InvoiceTimeline> timeline, Operator operator, Client clientSender, Client clientRecipient, Station stationRecipient) {
        this.id = id;
        this.quantity = quantity;
        this.deliveryType = deliveryType;
        this.type = type;
        this.datetime = datetime;
        this.packages = packages;
        this.timeline = timeline;
        this.operator = operator;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
        this.stationRecipient = stationRecipient;
    }

    public Invoice(int quantity, String deliveryType, @NotBlank(message = "Package type is null or empty") PackageType[] type, Date datetime, Collection<Package> packages, Collection<InvoiceTimeline> timeline, Operator operator, Client clientSender, Client clientRecipient, Station stationRecipient) {
        this.quantity = quantity;
        this.deliveryType = deliveryType;
        this.type = type;
        this.datetime = datetime;
        this.packages = packages;
        this.timeline = timeline;
        this.operator = operator;
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
        this.stationRecipient = stationRecipient;
    }



    public String completeName(Client client) {
        return client.getFirstname() + " " + client.getPatronymic() + " " + client.getSurname();
    }

    public double wight() {
        return getPackages().stream().map(Package::getWeight).reduce(Double::sum).orElse(0.0);
    }
    public double volume() {
        return getPackages().stream().map(Package::getVolume).reduce(Double::sum).orElse(0.0);
    }

    public double insurance() {
        return getPackages().stream().map(Package::getInsurance).reduce(Double::sum).orElse(0.0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String deliveryType() {
        return deliveryType.toString();
    }

    public void setDeliveryType(InvoiceType deliveryType) {
        this.deliveryType = deliveryType.toString();
    }

    public PackageType[] getType() {
        return type;
    }

    public String types() {
        return new PackageTypeArrayOfEnumToStringConvertor().convertToDatabaseColumn(type);
    }

    public void setType(PackageType[] type) {
        this.type = type;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Collection<Package> getPackages() {
        return packages;
    }

    public void setPackages(Collection<Package> packages) {
        this.packages = packages;
    }

    public Collection<InvoiceTimeline> getTimeline() {
        return timeline;
    }

    public void setTimeline(Collection<InvoiceTimeline> timeline) {
        this.timeline = timeline;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Client getClientSender() {
        return clientSender;
    }

    public void setClientSender(Client clientSender) {
        this.clientSender = clientSender;
    }

    public Client getClientRecipient() {
        return clientRecipient;
    }

    public void setClientRecipient(Client clientRecipient) {
        this.clientRecipient = clientRecipient;
    }

    public Station getStationRecipient() {
        return stationRecipient;
    }

    public void setStationRecipient(Station stationRecipient) {
        this.stationRecipient = stationRecipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (Double.compare(invoice.getQuantity(), getQuantity()) != 0) return false;
        if (getDeliveryType() != invoice.getDeliveryType()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getType(), invoice.getType())) return false;
        if (getDatetime() != null ? !getDatetime().equals(invoice.getDatetime()) : invoice.getDatetime() != null)
            return false;
        if (getPackages() != null ? !getPackages().equals(invoice.getPackages()) : invoice.getPackages() != null)
            return false;
        if (getOperator() != null ? !getOperator().equals(invoice.getOperator()) : invoice.getOperator() != null)
            return false;
        if (getClientSender() != null ? !getClientSender().equals(invoice.getClientSender()) : invoice.getClientSender() != null)
            return false;
        if (getClientRecipient() != null ? !getClientRecipient().equals(invoice.getClientRecipient()) : invoice.getClientRecipient() != null)
            return false;
        return getStationRecipient() != null ? getStationRecipient().equals(invoice.getStationRecipient()) : invoice.getStationRecipient() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getQuantity());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getDeliveryType() != null ? getDeliveryType().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getType());
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        result = 31 * result + (getPackages() != null ? getPackages().hashCode() : 0);
        result = 31 * result + (getOperator() != null ? getOperator().hashCode() : 0);
        result = 31 * result + (getClientSender() != null ? getClientSender().hashCode() : 0);
        result = 31 * result + (getClientRecipient() != null ? getClientRecipient().hashCode() : 0);
        result = 31 * result + (getStationRecipient() != null ? getStationRecipient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", deliveryType=" + deliveryType +
//                ", type=" + Arrays.toString(type) +
                ", datetime=" + datetime +
//                ", packages=" + packages +
//                ", timeline=" + timeline +
                ", operator=" + operator +
//                ", clientSender=" + clientSender +
//                ", clientRecipient=" + clientRecipient +
//                ", stationRecipient=" + stationRecipient +
                '}';
    }
}
