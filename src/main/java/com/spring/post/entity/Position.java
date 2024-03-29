package com.spring.post.entity;

import com.spring.post.entity.ancestor.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "position")
@Table(name = "position",  schema = "public",
        uniqueConstraints = @UniqueConstraint(name = "uk_position_name", columnNames = "name")
)
@NoArgsConstructor
@Getter
@Setter
public class Position implements Serializable {

    private static final String defaultPrice = "4250.0";
    private static final String maxPrice = "100000";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",  nullable = false)
    @NotBlank(message = "Position name cannot be null or empty")
    private String name;

    @Column(name = "price", nullable = false)
    @ColumnDefault(value = defaultPrice)
    @NumberFormat(pattern = "000000.000", style = NumberFormat.Style.NUMBER)
    @DecimalMin(message = "Position price is less than required", value = defaultPrice)
    @DecimalMax(message = "Position price is more than necessary", value = maxPrice)
    @Digits(    message = "Position price can be up to 50 ", integer = 5, fraction = 2)
    @Positive(  message = "Position must have volume greater than 1")
    private double price;


    @OneToMany(mappedBy = "position")
    @Size(    message = "Position employees must be greater  than or equal 0")
    @NotNull( message = "Position employees cannot be null")
    private Collection<Employee> employees;


    public Position(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Position(String name, double price) {
        this.name = name;
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        return getName() != null ? getName().equals(position.getName()) : position.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", employees=" + employees +
                '}';
    }
}

