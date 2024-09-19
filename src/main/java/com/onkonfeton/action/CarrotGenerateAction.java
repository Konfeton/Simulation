package com.onkonfeton.action;

import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.stationary.Carrot;

public class CarrotGenerateAction extends HerbivoreFoodGenerateAction {

    private static final float DEFAULT_SPAWN_RATE = 0.02f;

    public CarrotGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
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
