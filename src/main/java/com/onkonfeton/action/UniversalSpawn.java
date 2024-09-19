package com.onkonfeton.action;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.Random;
import java.util.function.Supplier;

public class UniversalSpawn extends Action {

    private final float percentToSpawn;
    private final Supplier<? extends Entity> supplier;

    public UniversalSpawn(float percentToSpawn, Supplier<? extends Entity> supplier) {
        this.percentToSpawn = percentToSpawn;
        this.supplier = supplier;
    }

    @Override
    public void perform(WorldMap map) {
        int numberToSpawn = getNumberToSpawn(map);
        for (int i = 0; i <= numberToSpawn; i++) {
            Coordinates coordinates = getRandomEmptyCoordinates(map);
            map.placeEntity(coordinates, supplier.get());
        }
    }

    protected int getNumberToSpawn(WorldMap map){
        int mapSize = map.getMaxWorldX() * map.getMaxWorldY();
        int number = (int) Math.ceil(mapSize * percentToSpawn);
        return number;
    }

    private Coordinates getRandomEmptyCoordinates(WorldMap map) {
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
