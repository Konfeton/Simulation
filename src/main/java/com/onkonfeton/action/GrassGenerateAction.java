package com.onkonfeton.action;

import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.stationary.Grass;

public class GrassGenerateAction extends HerbivoreFoodGenerateAction {

    private static final float DEFAULT_SPAWN_RATE = 0.06f;

    public GrassGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
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
