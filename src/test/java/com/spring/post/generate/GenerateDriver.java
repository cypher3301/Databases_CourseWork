package com.spring.post.generate;

import com.spring.post.entity.Driver;

public class GenerateDriver extends GenerateEmployee{

    public static Driver getNewDriver() {
        GenerateDriver generateDriver = new GenerateDriver();
        String[] fio = generateDriver.generateFIO();
        return new Driver(generateDriver.generateId(), fio[0], fio[1], fio[2], generateDriver.generatePhone(), generateDriver.generateEmail(), generateDriver.generatePriceCardNumber(), generateDriver.generateAddress(), generateDriver.generateIdentificationCode(), null, GenerateCar.getNewRandomCar());
    }
}
