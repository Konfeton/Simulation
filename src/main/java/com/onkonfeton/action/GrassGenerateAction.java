package com.onkonfeton.action;

import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.stationary.Grass;

public class GrassGenerateAction extends FoodGenerateAction{

    public GrassGenerateAction() {
        spawnRate = 0.06f;
    }

    @Override
    protected int getCurrentNumber(WorldMap map) {
        return map.getEntitiesOfType(Grass.class).keySet().size();
    }

    @Override
    protected Grass getEntity() {
        return new Grass();
    }
}
