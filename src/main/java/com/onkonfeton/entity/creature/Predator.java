package com.onkonfeton.entity.creature;

import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;

public class Predator extends Creature {
    private final static int DEFAULT_SPEED = 2;
    private final static int DEFAULT_HEALTH = 5;
    private final static int DEFAULT_HUNGER_POINTS = 4;
    private final static int DEFAULT_STRENGTH = 2;

    private int strength;

    public Predator() {
        super(DEFAULT_SPEED, DEFAULT_HEALTH, DEFAULT_HUNGER_POINTS, Herbivore.class);
        this.strength = DEFAULT_STRENGTH;
    }

    @Override
    protected void eat(Coordinates coordinates, WorldMap map) {
        Herbivore herbivore = (Herbivore) map.getEntityByCoordinates(coordinates);
        herbivore.health -= this.strength;
        if (herbivore.health <= 0) {
            map.removeEntity(coordinates);
            hungerPoints = DEFAULT_HUNGER_POINTS;
            this.health += 1;
        }
    }
}
