package com.spring.post.generate;

import com.spring.post.entity.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateCar extends Generator {

    private static List<String> carMarks;

    private static void carMarksInList() {
        carMarks = Arrays.asList("Mercedes", "Benz", "Man", "Daf", "Scania", "Iveco", "Volvo", "Renault", "Nissan", "Tatra", "Mitsubishi");
    }




    protected Double generateWeight(double start, double end) {
        return new Random().nextDouble() * Math.pow(10, start) % end;
    }

    protected Double generateVolume(double start, double end) {
        return generateWeight(start, end);
    }

    private String generateCarMark() {
        carMarksInList();
        return getRandomStringFromList(carMarks);
    }

    private String generateCarNumber() {
        return generateBigSymbol() + generateBigSymbol() + Math.abs(new Random().nextInt() % 10000 - 1) + generateBigSymbol() + generateBigSymbol();
    }

    public Car generateNewCar() {
        return Car.builder()
                .transportedWeight(generateWeight(20, 3000))
                .transportedVolume(generateVolume(1, 50))
                .mark(generateCarMark())
                .carNumber(generateCarNumber())
                .build();
    }

    public static Car getNewRandomCar() {
        return new GenerateCar().generateNewCar();
    }
}
