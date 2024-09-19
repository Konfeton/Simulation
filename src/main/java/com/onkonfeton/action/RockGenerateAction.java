package com.onkonfeton.action;

import com.onkonfeton.entity.stationary.Rock;

public class RockGenerateAction extends SpawnAction<Rock> {

    private static final float DEFAULT_SPAWN_RATE = 0.03f;

    public RockGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
    }

    @Override
    protected Rock getEntity() {
        return new Rock();
    }
}
