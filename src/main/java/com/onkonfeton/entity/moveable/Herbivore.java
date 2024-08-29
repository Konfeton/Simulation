package com.onkonfeton.entity.moveable;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;
import com.onkonfeton.entity.stationary.HerbivoreFood;

public class Herbivore extends Creature{

    public Herbivore() {
        speed = 1;
        health = 1;
        target = HerbivoreFood.class;
    }

    public Herbivore(int speed, int health) {
        super(speed, health);
    }

    @Override
    protected void eat(Coordinates lastStep, WorldMap map) {
        Entity food = map.getEntityByCoordinates(lastStep);
        int satiety = ((HerbivoreFood) food).getSatiety();
        health += satiety;
        map.removeEntity(lastStep);
    }


}
