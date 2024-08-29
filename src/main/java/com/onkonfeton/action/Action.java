package com.onkonfeton.action;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;

import java.util.Random;

public abstract class Action{
    public abstract void perform(WorldMap map);

    protected Coordinates getRandomEmptyCoordinates(WorldMap map) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(map.getMaxWorldX());
            int y = random.nextInt(map.getMaxWorldY());
            Coordinates coordinates = new Coordinates(x, y);
            if (map.isSquareEmpty(coordinates)) {
                return coordinates;
            }
        }
    }

}
