package com.example.demo.dao.entity;

import com.example.demo.dao.entity.ancestor.Employee;
import com.example.demo.dao.entity.ancestor.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "position")
@Table(name = "position", catalog = "postOffice", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "position")
    private Collection<Employee> employees;

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
}

