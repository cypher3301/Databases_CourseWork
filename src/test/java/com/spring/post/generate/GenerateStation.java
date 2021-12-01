package com.spring.post.generate;

import com.spring.post.entity.Invoice;
import com.spring.post.entity.Operator;
import com.spring.post.entity.Station;
import com.spring.post.entity.WorkShift;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenerateStation extends Generator{
    public static Station getNewStation() {
        Station station = new Station();
        GenerateEmployee generator = new GenerateEmployee();
        station.setAddress(generator.generateAddress());
        station.setId(generator.generateId());
        station.setNumber(Short.parseShort(generator.generateNumber(2)));

        Collection<Operator> employees = station.getEmployees();
        if (employees==null) employees = new ArrayList<>();
        employees.add(GenerateOperator.getNewOperator());
//        station.getInvoices().add(null);
//        station.getWorkShifts().add(null);

        return station;
    }

    public static Station getNewStation(Operator operator) {
        Station station = new Station();
        GenerateEmployee generator = new GenerateEmployee();
        station.setAddress(generator.generateAddress());
        station.setId(generator.generateId());
        station.setNumber(Short.parseShort(generator.generateNumber(2)));

        Collection<Operator> employees = station.getEmployees();
        if (employees==null) employees = new ArrayList<>();
        employees.add(operator);
//        station.getInvoices().add(null);
//        station.getWorkShifts().add(null);

        return station;
    }

    public static Station getNewBaseStation() {
        Station station = new Station();
        GenerateEmployee generator = new GenerateEmployee();
        station.setAddress(generator.generateAddress());
        station.setId(generator.generateId());
        station.setNumber(Short.parseShort(generator.generateNumber(2)));
        return station;
    }

    public static Station getNewStation(List<Invoice> invoices, List<WorkShift> workShifts) {
        Station station = new Station();
        GenerateEmployee generator = new GenerateEmployee();
        station.setAddress(generator.generateAddress());
        station.setId(generator.generateId());
        station.setNumber(Short.parseShort(generator.generateNumber(2)));

        Collection<Operator> employees = station.getEmployees();
        if (employees==null) employees = new ArrayList<>();
        employees.add(GenerateOperator.getNewOperator());
        station.setInvoices(invoices);
        station.setWorkShifts(workShifts);
        return station;
    }
}
