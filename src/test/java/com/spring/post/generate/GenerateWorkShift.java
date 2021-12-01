package com.spring.post.generate;

import com.spring.post.entity.Operator;
import com.spring.post.entity.Station;
import com.spring.post.entity.WorkShift;
import com.spring.post.entity.status.WorkShiftType;

import java.util.Random;

public class GenerateWorkShift extends Generator{

    private WorkShiftType generateWorkShiftType() {

        int i = new Random().nextInt() % 2;
        if (i==0){
            return WorkShiftType.STARTED;
        }
        return WorkShiftType.COMPLETED;
    }

    public static WorkShift getNewWorkShift() {
        GenerateWorkShift generateWorkShift = new GenerateWorkShift();
        WorkShift workShift = new WorkShift();
        workShift.setId(generateWorkShift.generateId());
        workShift.setDatetime(generateWorkShift.generateDatetime());
        workShift.setType(generateWorkShift.generateWorkShiftType());
        workShift.setOperator(GenerateOperator.getNewOperator());
        return workShift;
    }

    public static WorkShift getNewWorkShift(Operator operator) {
        GenerateWorkShift generateWorkShift = new GenerateWorkShift();
        WorkShift workShift = new WorkShift();
        workShift.setId(generateWorkShift.generateId());
        workShift.setDatetime(generateWorkShift.generateDatetime());
        workShift.setType(generateWorkShift.generateWorkShiftType());
        workShift.setOperator(operator);
        workShift.setStation(operator.getStation());
        return workShift;
    }
}
