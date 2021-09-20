package com.spring.post.generate;

import com.spring.post.entity.Client;

public class GenerateClient extends GeneratePerson{

    public static Client generateNewClient() {
        GeneratePerson generatePerson = new GeneratePerson();
        String[] fio = generatePerson.generateFIO();
        return new Client(
                generatePerson.generateId(),
                fio[0],
                fio[1],
                fio[2],
                generatePerson.generatePhone());
    }
}
