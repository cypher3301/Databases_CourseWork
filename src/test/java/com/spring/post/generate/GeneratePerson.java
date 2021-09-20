package com.spring.post.generate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneratePerson extends Generator{
    private static final List<String> firstname;
    private static final List<String> patronymic;
    private static final List<String> lastname;

    static  {
        firstname = Arrays.asList("Владислав", "Антон", "Дмитрий", "Иван", "Валентин", "Богдан", "Виталий", "Ирина", "Катерина", "Валентина", "Анна", "Дарья", "Виктория");
        patronymic = Arrays.asList("Владиславович", "Антонович", "Дмитриевич", "Иванович", "Валентинович", "Богданович", "Виталиевич", "Сергеевич", "Русланович");
        lastname = Arrays.asList("Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов", "Новиков", "Морозов", "Петров");
    }


    public String generateFIO(String separator) {
//        putFIOInList();
        return "" +
                getRandomStringFromList(firstname) + separator +
                getRandomStringFromList(patronymic) + separator +
                getRandomStringFromList(lastname);
    }

    public String[] generateFIO() {
//        putFIOInList();
        return new String[]{getRandomStringFromList(firstname), getRandomStringFromList(patronymic), getRandomStringFromList(lastname),};
    }

    public String generatePhone() {
        return "+380" + generateNumber(9);
    }

    public Long generateId() {
        return Math.abs(new Random().nextLong());
    }


}
