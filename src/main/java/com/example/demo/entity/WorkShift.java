package com.example.demo.entity;

import com.example.demo.entity.abstraction.Work;
import com.example.demo.entity.status.WorkShiftStatus;
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
public class WorkShift implements Work {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_shift_id", table = "work_shift")
    protected long id;

    @NotNull
    @Column(name = "status", length = 10, table = "work_shift")
    @Enumerated(EnumType.STRING)
    protected WorkShiftStatus status;

    @NotNull
    @Column(name = "start_time", nullable = false, table = "work_shift")
    protected Timestamp time;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id", foreignKey = @ForeignKey(name = "fk_work_shift_operator_id"))
    protected Operator operator;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "work_shift_packages"
    )
    protected Collection<Package> packages = new ArrayList<>();

    public WorkShift(@NotNull Operator operator, Collection<Package> packagesStartShift) {
        this.operator = operator;
        this.packages = packagesStartShift;
        if (operator != null) {
            this.setTime(new Timestamp(System.nanoTime()));
        }
    }

    public WorkShift(@NotNull Operator operator) {
        this.operator = operator;
        if (operator != null) {
            this.setTime(new Timestamp(System.nanoTime()));
        }
    }
}

