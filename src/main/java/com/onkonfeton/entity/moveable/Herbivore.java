package com.onkonfeton.entity.moveable;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;
import com.onkonfeton.entity.stationary.HerbivoreFood;

public class Herbivore extends Creature{

    public Herbivore() {
        speed = 1;
        health = 4;
        hungerPoints = 3;
        target = HerbivoreFood.class;
    }

    @Override
    protected void eat(Coordinates lastStep, WorldMap map) {
        Entity food = map.getEntityByCoordinates(lastStep);
        int healingPower = ((HerbivoreFood) food).getHealingPower();
        health += healingPower;
        hungerPoints = BASE_HUNGER_POINTS;
        map.removeEntity(lastStep);
    }
}
