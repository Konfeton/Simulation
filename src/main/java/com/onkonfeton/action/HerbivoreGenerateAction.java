package com.onkonfeton.action;

import com.onkonfeton.entity.moveable.Herbivore;

public class HerbivoreGenerateAction extends SpawnAction<Herbivore> {

    public HerbivoreGenerateAction() {
        spawnRate = 0.05f;
    }

    @Override
    protected Herbivore getEntity() {
        return new Herbivore();
    }
}
