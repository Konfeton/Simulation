package com.onkonfeton.entity.moveable;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;

public class Predator extends Creature{
    private int strength;

    public Predator() {
        speed = 2;
        health = 2;
        strength = 2;
        target = Herbivore.class;
    }

    public Predator(int speed, int health) {
        super(speed, health);
    }


    @Override
    protected void eat(Coordinates coordinates, WorldMap map) {
        Herbivore herbivore = (Herbivore) map.getEntityByCoordinates(coordinates);
        herbivore.health -= this.strength;
        if (herbivore.health <= 0){
            map.removeEntity(coordinates);
        }
    }
}
