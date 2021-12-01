package com.spring.post.generate;

import com.spring.post.entity.Car;
import com.spring.post.entity.Driver;
import com.spring.post.entity.Position;

public class GenerateDriver extends GenerateEmployee{

    public static Driver getNewDriver() {
        GenerateDriver generateDriver = new GenerateDriver();
        String[] fio = generateDriver.generateFIO();
        Driver driver = new Driver(generateDriver.generateId(), fio[0], fio[1], fio[2], generateDriver.generatePhone(), generateDriver.generateEmail(), generateDriver.generatePriceCardNumber(), generateDriver.generateAddress(), generateDriver.generateIdentificationCode(), null, GenerateCar.getNewRandomCar());
        driver.setPosition(Positions.getDriverPosition());
        return driver;
    }

    public static Driver getNewDriver(Car car) {
        GenerateDriver generateDriver = new GenerateDriver();
        String[] fio = generateDriver.generateFIO();
        Driver driver = new Driver(generateDriver.generateId(), fio[0], fio[1], fio[2], generateDriver.generatePhone(), generateDriver.generateEmail(), generateDriver.generatePriceCardNumber(), generateDriver.generateAddress(), generateDriver.generateIdentificationCode(), null, car);
        driver.setPosition(Positions.getDriverPosition());
        return driver;
    }

    public static Driver getNewDriver(Car car, Position position) {
        GenerateDriver generateDriver = new GenerateDriver();
        String[] fio = generateDriver.generateFIO();
        Driver driver = new Driver(generateDriver.generateId(), fio[0], fio[1], fio[2], generateDriver.generatePhone(), generateDriver.generateEmail(), generateDriver.generatePriceCardNumber(), generateDriver.generateAddress(), generateDriver.generateIdentificationCode(), null, car);
        driver.setPosition(position);
        return driver;
    }
}
