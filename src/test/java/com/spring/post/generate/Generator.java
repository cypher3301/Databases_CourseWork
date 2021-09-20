package com.spring.post.generate;

import com.spring.post.entity.Car;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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






}
