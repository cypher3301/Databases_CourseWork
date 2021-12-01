package com.spring.post.generate;

import com.spring.post.entity.Operator;
import com.spring.post.entity.Station;

public class GenerateOperator extends GenerateEmployee{

    private String generateLogin() {
        return generateRandomString(8);
    }

    public static Operator getNewOperator() {
        GenerateOperator generateOperator = new GenerateOperator();
        String[] fio = generateOperator.generateFIO();
        Operator operator = new Operator(generateOperator.generateId(), fio[0], fio[1], fio[2], generateOperator.generatePhone(), generateOperator.generateEmail(), generateOperator.generatePriceCardNumber(), generateOperator.generateAddress(), generateOperator.generateIdentificationCode(), null, null);
        operator.setLogin(generateOperator.generateLogin());
        operator.setPosition(Positions.getOperatorPosition());
        operator.setStation(GenerateStation.getNewStation());
        return operator;
    }

    public static Operator getNewOperator(Station station) {
        GenerateOperator generateOperator = new GenerateOperator();
        String[] fio = generateOperator.generateFIO();
        Operator operator = new Operator(generateOperator.generateId(), fio[0], fio[1], fio[2], generateOperator.generatePhone(), generateOperator.generateEmail(), generateOperator.generatePriceCardNumber(), generateOperator.generateAddress(), generateOperator.generateIdentificationCode(), Positions.getOperatorPosition(), station);
        operator.setLogin(generateOperator.generateLogin());
        return operator;
    }
}
