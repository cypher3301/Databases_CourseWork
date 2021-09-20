package com.spring.post.generate;

import com.spring.post.entity.Operator;

public class GenerateOperator extends GenerateEmployee{

    private String generateLogin() {
        return generateRandomString(8);
    }

    public static Operator getNewOperator() {
        GenerateOperator generateOperator = new GenerateOperator();
        String[] fio = generateOperator.generateFIO();
        Operator operator = new Operator(generateOperator.generateId(), fio[0], fio[1], fio[2], generateOperator.generatePhone(), generateOperator.generateEmail(), generateOperator.generatePriceCardNumber(), generateOperator.generateAddress(), generateOperator.generateIdentificationCode(), null, null);
        operator.setLogin(generateOperator.generateLogin());
        return operator;
    }
}
