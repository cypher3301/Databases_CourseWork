package com.spring.post.test;


import com.spring.post.entity.Operator;
import com.spring.post.entity.Station;
import com.spring.post.generate.*;
import org.junit.jupiter.api.Test;

public class TestGenerators {

    @Test
    void testCarGenerator() {
        System.out.println(GenerateCar.getNewRandomCar().toString());
    }

    @Test
    void testClientGenerator() {
        System.out.println(GenerateClient.generateNewClient().toString());
    }

    @Test
    void testDriverGenerator() {
        System.out.println(GenerateDriver.getNewDriver().toString());
    }

    @Test
    void testOperatorGenerator() {
        System.out.println(GenerateOperator.getNewOperator().toString());
    }


    @Test
    void testStationGenerator() {
        System.out.println(GenerateStation.getNewStation().toString());
    }


    @Test
    void testWorkShiftGenerator() {
//        System.out.println(GenerateWorkShift.getNewWorkShift().toString());
//        System.out.println(GenerateWorkShift.getNewWorkShift(GenerateOperator.getNewOperator()).toString());
        Station newBaseStation = GenerateStation.getNewBaseStation();
        Operator newOperator = GenerateOperator.getNewOperator(newBaseStation);
        System.out.println(GenerateWorkShift.getNewWorkShift(newOperator).toString());
    }
}
