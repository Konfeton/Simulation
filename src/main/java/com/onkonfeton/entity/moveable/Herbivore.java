package com.onkonfeton.entity.moveable;

import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.Entity;
import com.onkonfeton.entity.stationary.HerbivoreFood;

public class Herbivore extends Creature {
    private final static int DEFAULT_SPEED = 1;
    private final static int DEFAULT_HEALTH = 4;
    private static final int DEFAULT_HUNGER_POINTS = 3;

    public Herbivore() {
        super(DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_HUNGER_POINTS, HerbivoreFood.class);
    }

    @Override
    protected void eat(Coordinates lastStep, WorldMap map) {
        Entity food = map.getEntityByCoordinates(lastStep);
        int healingPower = ((HerbivoreFood) food).getHealingPower();
        health += healingPower;
        hungerPoints = DEFAULT_HUNGER_POINTS;
        map.removeEntity(lastStep);
    }
}
