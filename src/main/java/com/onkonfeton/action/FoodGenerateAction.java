package com.onkonfeton.action;

import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.stationary.HerbivoreFood;

public abstract class FoodGenerateAction extends SpawnAction<HerbivoreFood> {

    @Override
    protected int getNumberToSpawn(WorldMap map) {
        int currentNumber = getCurrentNumber(map);
        int mapSize = map.getMaxWorldX() * map.getMaxWorldY();
        float currentPercent = currentNumber / (mapSize * 1.0f);
        spawnRate -= currentPercent;
        int number = (int) Math.ceil(mapSize * spawnRate);
        return number;
    }

    protected abstract int getCurrentNumber(WorldMap map);
}
