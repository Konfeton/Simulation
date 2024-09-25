package com.onkonfeton.simulation;

import com.onkonfeton.entity.Entity;
import com.onkonfeton.entity.moveable.Herbivore;
import com.onkonfeton.entity.stationary.Grass;
import com.onkonfeton.map.WorldMap;

public class SpawnRates {
    private final float carrotSpawnRate = 0.02f;
    private final float grassSpawnRate = 0.06f;
    private final float herbivoreSpawnRate = 0.06f;
    private final float predatorSpawnRate = 0.04f;
    private final float rockSpawnRate = 0.03f;
    private final float treeSpawnRate = 0.03f;
    private final WorldMap map;

    public SpawnRates(WorldMap map) {
        this.map = map;
    }

    private int convertToNumber(double spawnRate) {
        int mapSize = map.getMapSize();
        return (int) Math.ceil(mapSize * spawnRate);
    }

    public int getNumberToSpawn(Class<? extends Entity> clazz){
        if (clazz.isInstance(Grass.class)){
            return convertToNumber(grassSpawnRate);
        }
    }
}
