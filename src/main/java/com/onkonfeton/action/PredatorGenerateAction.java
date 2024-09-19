package com.onkonfeton.action;

import com.onkonfeton.entity.moveable.Predator;

public class PredatorGenerateAction extends SpawnAction<Predator> {

    private static final float DEFAULT_SPAWN_RATE = 0.04f;

    public PredatorGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
    }

    @Override
    protected Predator getEntity() {
        return new Predator();
    }
}
