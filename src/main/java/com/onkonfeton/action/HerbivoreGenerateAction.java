package com.onkonfeton.action;

import com.onkonfeton.entity.moveable.Herbivore;

public class HerbivoreGenerateAction extends SpawnAction<Herbivore> {

    private static final float DEFAULT_SPAWN_RATE = 0.05f;

    public HerbivoreGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
    }
    @Override
    protected Herbivore getEntity() {
        return new Herbivore();
    }
}
