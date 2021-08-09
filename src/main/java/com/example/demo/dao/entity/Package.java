package com.example.demo.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


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




    @NotNull
    @Column(name = "weight",    nullable = false)
    private double weight;
    @NotNull
    @Column(name = "volume",    nullable = false)
    private double volume;
    @NotNull
    @Column(name = "insurance", nullable = false)
    @Min(200)
    @Max(100000)
    private double insurance;
    @NotNull
    private String type;


    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "package_invoice"))
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package)) return false;

        Package aPackage = (Package) o;

        if (Double.compare(aPackage.getWeight(), getWeight()) != 0) return false;
        if (Double.compare(aPackage.getVolume(), getVolume()) != 0) return false;
        if (Double.compare(aPackage.getInsurance(), getInsurance()) != 0) return false;
        if (!getType().equals(aPackage.getType())) return false;
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
        result = 31 * result + getType().hashCode();
        result = 31 * result + (getInvoice() != null ? getInvoice().hashCode() : 0);
        return result;
    }
}
