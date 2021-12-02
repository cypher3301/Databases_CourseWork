package com.spring.post.generate;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class Generator {




    public Generator() {

    }

    protected String getRandomStringFromList(@NotNull List items) {
        Random random = new Random();
        int randomPosition = Math.abs(random.nextInt()) % items.size();
        return (String) items.get(randomPosition);
    }

    protected String generateBigSymbol() {
        char randSymbol = (char) (Math.abs(new Random().nextInt())%25+65);
        return String.valueOf(randSymbol);
    }

    protected String generateSmallSymbol() {
        char randSymbol = (char) (Math.abs(new Random().nextInt())%25+97);
        return String.valueOf(randSymbol);
    }

    protected String generateBigOrSmallSymbol() {
        return new Random().nextInt()%2==0?generateBigSymbol():generateSmallSymbol();
    }

    protected String generateRandomString(int length) {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            s.append(generateBigOrSmallSymbol());
        }
        return s.toString();
    }


    protected String generateNumber(int length) {
        Random random = new Random();
        long randValue= Math.abs(random.nextLong());
        int randValueLength = String.valueOf(randValue).length();
        if (randValueLength>length){
            randValue/=Math.pow(10,randValueLength-length);
        } else if(randValueLength<length) {
            randValue*=Math.pow(10,length-randValueLength);
        }
        return String.valueOf(randValue);
    }

    protected Timestamp generateDatetime() {
        Random random = new Random();
        long nowTime = System.nanoTime();
        long randTime = random.nextLong();
        return new Timestamp(randTime%nowTime);
    }

    protected Long generateId() {
        return Math.abs(new Random().nextLong());
    }







}
