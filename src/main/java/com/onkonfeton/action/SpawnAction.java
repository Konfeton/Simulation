package com.onkonfeton.action;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;

public abstract class SpawnAction<T extends Entity> extends Action {

    protected float spawnRate;

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

    protected abstract T getEntity();

}
