package com.onkonfeton.entity.moveable;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;

public class Predator extends Creature{
    private final int strength;

    public Predator() {
        speed = 2;
        health = 5;
        strength = 2;
        target = Herbivore.class;
    }

    @Override
    protected void eat(Coordinates coordinates, WorldMap map) {
        Herbivore herbivore = (Herbivore) map.getEntityByCoordinates(coordinates);
        herbivore.health -= this.strength;
        if (herbivore.health <= 0){
            map.removeEntity(coordinates);
            hungerPoints = BASE_HUNGER_POINTS;
            this.health += 1;
        }
    }
}
