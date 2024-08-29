package com.onkonfeton.action;

import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.stationary.Carrot;

public class CarrotGenerateAction extends FoodGenerateAction {

    public CarrotGenerateAction() {
        spawnRate = 0.02f;
    }

    @Override
    protected Carrot getEntity() {
        return new Carrot();
    }

    @Override
    protected int getCurrentNumber(WorldMap map) {
        return map.getEntitiesOfType(Carrot.class).keySet().size();
    }
}
