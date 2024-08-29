package com.onkonfeton.action;

import com.onkonfeton.entity.stationary.Rock;

public class RockGenerateAction extends SpawnAction<Rock> {

    public RockGenerateAction() {
        spawnRate = 0.03f;
    }

    @Override
    protected Rock getEntity() {
        return new Rock();
    }
}
