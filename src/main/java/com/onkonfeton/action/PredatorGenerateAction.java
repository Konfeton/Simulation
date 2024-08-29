package com.onkonfeton.action;

import com.onkonfeton.entity.moveable.Predator;

public class PredatorGenerateAction extends SpawnAction<Predator> {

    public PredatorGenerateAction() {
        spawnRate = 0.04f;
    }

    @Override
    protected Predator getEntity() {
        return new Predator();
    }
}
