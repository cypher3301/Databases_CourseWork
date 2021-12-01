package com.spring.post.generate;

import com.spring.post.entity.Position;

public class Positions extends Generator {
    public static Position getOperatorPosition() {
        Positions positions = new Positions();
        Position position = new Position();

        position.setId(5678436582674L);
        position.setName("Operator");
        position.setPrice(7000);
        return position;
    }



    public static Position getDriverPosition() {
        Positions positions = new Positions();
        Position position = new Position();
        position.setId(5678436635284L);
        position.setName("Driver");
        position.setPrice(9000);

        return position;
    }
}
