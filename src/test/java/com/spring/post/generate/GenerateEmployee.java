package com.spring.post.generate;

import com.spring.post.entity.embeddable.Address;

import java.util.*;

public class GenerateEmployee extends GeneratePerson {

    private static final List<String> regions;
    private static final List<String> cities;
    private static final List<String> streets;
//    private static final List<String> buildings;
    private static final List<String> campuses;
//    private static final List<String> apartments;




    static {
        regions = Arrays.asList("Одесская", "Днепропетровская", "Черниговская", "Харьковская", "Житомирская", "Полтавская", "Херсонская", "Киевская", "Запорожская", "Луганская", "Донецкая", "Винницкая", "АР Крым", "Кировоградская", "Николаевская", "Сумская", "Львовская", "Черкасская", "Хмельницкая", "Волынская", "Ровенская", "Ивано-Франковская", "Тернопольская", "Закарпатская", "Черновицкая", "Севастополь", "Киев");
        cities = Arrays.asList("Днепр","Донецк","Запорожье","Львов","Кривой Рог","Севастополь","Николаев","Мариуполь");
        streets = Arrays.asList("Улица Дерибасовская","Улица Крещатик","Андреевский спуск","Улица Сумская","Улица Главная","Кривой Рог");
//        buildings = Arrays.asList();
        campuses = Arrays.asList("A", "B", "C",  "D", "E", "F", null );
//        apartments = Arrays.asList();
    }

    public GenerateEmployee() {
    }

    protected String generatePriceCardNumber() {
        return generateNumber(16);
    }

    protected String generateIdentificationCode() {
        return generateNumber(10);
    }

    protected String generateEmail() {
        String username = generateRandomString(Math.abs(new Random().nextInt()) % 54 + 10);
        String service = generateRandomString(Math.abs(new Random().nextInt()) % 5 + 3);
        String domain = generateRandomString(Math.abs(new Random().nextInt()) % 3 + 2);
        return username + "@" + service + "." + domain;
    }

    protected Address generateAddress() {

        return Address.builder()
                .region(getRandomStringFromList(regions))
                .city(getRandomStringFromList(cities))
                .street(getRandomStringFromList(streets))
                .building(String.valueOf(Math.abs(new Random().nextInt(9999))))
                .campus(getRandomStringFromList(campuses))
                .apartment(String.valueOf(Math.abs(new Random().nextInt(9999))))
                .build();
    }
}
