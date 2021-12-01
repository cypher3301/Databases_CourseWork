package com.spring.post.generate;

import com.spring.post.entity.Car;
import com.spring.post.entity.Driver;
import com.spring.post.entity.Position;
import com.spring.post.repository.kotlin.DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputToDB {
//    @Autowired
//    CarRepository carRepository;
//    @Autowired
//    StationRepository stationRepository;
//    @Autowired
//    DriverRepository driverRepository;
//
//    public InputToDB(DriverRepository driverRepository) {
//        this.driverRepository = driverRepository;
//    }
//
//    GenerateDriver generateDriver;
//    GenerateCar generateCar;
////    Positions positions;
////    GenerateStation generateStation;
//
//    @Test
//    void Driver() {
//        generateCar = new GenerateCar();
//        generateDriver = new GenerateDriver();
//        Position driverPosition = Positions.getDriverPosition();
//
//        for (int i = 0; i < 10; i++) {
//            Car car = generateCar.generateNewCar();
//            Driver newDriver = GenerateDriver.getNewDriver(car, driverPosition);
//            driverRepository.save(newDriver);
//        }
//
//    }


    @Test
    void name() {
        assert true;
    }
}
