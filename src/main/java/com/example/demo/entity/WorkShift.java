package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;



@Entity(name = "work_shift")
@Table(name = "work_shift", catalog = "postOffice", schema = "public")
@Data
@NoArgsConstructor
public class WorkShift implements Work{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_shift_id", table = "work_shift")
    protected Long id;

    @NotNull
    @Column(name = "status", length = 10, table = "work_shift")
    @Enumerated(EnumType.STRING)
    protected WorkShiftStatus status;

    @NotNull
    @Column(name = "start_time", nullable = false, table = "work_shift")
    protected Timestamp time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    protected Operator operator;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "work_shift_packages",
            joinColumns = @JoinColumn(name = "work_shift_id"),
            inverseJoinColumns = @JoinColumn(name = "packages_id")
    )
    protected Collection<Package> packages = new ArrayList<>();

    public WorkShift(@NotNull Operator operator, Collection<Package> packagesStartShift) {
        this.operator = operator;
        this.packages = packagesStartShift;
        if(operator!=null){
            this.setTime(new Timestamp(System.nanoTime()));
        }
    }

    public WorkShift(@NotNull Operator operator) {
        this.operator = operator;
        if(operator!=null){
            this.setTime(new Timestamp(System.nanoTime()));
        }
    }
}

