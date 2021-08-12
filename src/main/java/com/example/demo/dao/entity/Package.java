package com.example.demo.dao.entity;

import com.example.demo.dao.convertor.PackageTypeArrayOfEnumToStringConvertor;
import com.example.demo.dao.entity.status.PackageType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;


@Entity(name = "package")
@Table(name = "package", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @Column(name = "weight_kg",    nullable = false)
    @DecimalMin(value = "0",           message = "Package weight is less than required")
    @DecimalMax(value = "3000",        message = "Package weight is more than necessary")
    @Positive(                         message = "Package must have weight greater than 0")
    private double weight;

    @Column(name = "volume_m3",    nullable = false)
    @DecimalMin(value = "0",           message = "Package volume is less than required")
    @DecimalMax(value = "50",          message = "Package volume is more than necessary")
    @Positive(                         message = "Package must have volume greater than 0")
    private double volume;

    @Column(name = "insurance_uah", nullable = false)
    @ColumnDefault(value = "200")
    @DecimalMin(value = "200",         message = "Package insurance is less than required")
    @DecimalMax(value = "100000",      message = "Package insurance is more than necessary")
    @Positive(                         message = "Package insurance cannot be less 0")
    private double insurance;

    //index
//    @Type(type = "com.example.demo.dao.convertor.InvoiceTypeArrayOfEnumToStringConvertor")
    @Convert(converter = PackageTypeArrayOfEnumToStringConvertor.class)
    @Column(name = "type", nullable = false, length = 16)
    @NotBlank(message = "Package type is null or empty")
    private PackageType[] type;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "package_invoice"))
    @NotNull(message = "Package invoice cannot be null")
    private Invoice invoice;


    public Package(long id, double weight, double volume, double insurance, @NotBlank(message = "Package type is null or empty") PackageType[] type) {
        this.id = id;
        this.weight = weight;
        this.volume = volume;
        this.insurance = insurance;
        this.type = type;
    }

    public Package(double weight, double volume, double insurance, @NotBlank(message = "Package type is null or empty") PackageType[] type) {
        this.weight = weight;
        this.volume = volume;
        this.insurance = insurance;
        this.type = type;
    }

    //Regenerate equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package)) return false;

        Package aPackage = (Package) o;

        if (Double.compare(aPackage.getWeight(), getWeight()) != 0) return false;
        if (Double.compare(aPackage.getVolume(), getVolume()) != 0) return false;
        if (Double.compare(aPackage.getInsurance(), getInsurance()) != 0) return false;
        if (!Arrays.equals(getType(), aPackage.getType())) return false;
        return getInvoice() != null ? getInvoice().equals(aPackage.getInvoice()) : aPackage.getInvoice() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getWeight());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getVolume());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getInsurance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(getType());
        result = 31 * result + (getInvoice() != null ? getInvoice().hashCode() : 0);
        return result;
    }
}
