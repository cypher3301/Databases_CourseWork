package com.spring.post.test;


import com.spring.post.generate.GenerateCar;
import com.spring.post.generate.GenerateClient;
import com.spring.post.generate.GenerateDriver;
import com.spring.post.generate.GenerateOperator;
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
}
