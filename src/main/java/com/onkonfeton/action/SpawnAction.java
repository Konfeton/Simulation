package com.onkonfeton.action;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.Random;

public abstract class SpawnAction<T extends Entity> extends Action {

    protected float spawnRate;

    public SpawnAction(float spawnRate) {
        this.spawnRate = spawnRate;
    }

    @Override
    public void perform(WorldMap map) {
        int numberToSpawn = getNumberToSpawn(map);
        for (int i = 0; i <= numberToSpawn; i++) {
            Coordinates coordinates = getRandomEmptyCoordinates(map);
            map.placeEntity(coordinates, getEntity());
        }
    }

    protected int getNumberToSpawn(WorldMap map){
        int mapSize = map.getMaxWorldX() * map.getMaxWorldY();
        int number = (int) Math.ceil(mapSize * spawnRate);
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

    protected abstract T getEntity();
}
