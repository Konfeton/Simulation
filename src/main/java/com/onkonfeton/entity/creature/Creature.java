package com.onkonfeton.entity.creature;

import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.Entity;
import com.onkonfeton.path.BreadthFirstSearch;
import com.onkonfeton.path.Path;
import com.onkonfeton.path.PathFinder;

public abstract class Creature extends Entity {
    protected int speed;
    protected int health;
    protected int hungerPoints;
    protected Class<? extends Entity> target;
    private final PathFinder pathFinder = new BreadthFirstSearch();

    public Creature(int speed, int health, int hungerPoints, Class<? extends Entity> target) {
        this.speed = speed;
        this.health = health;
        this.hungerPoints = hungerPoints;
        this.target = target;
    }

    public void makeMove(Coordinates from, WorldMap map) {

        Path path = pathFinder.findPath(map, from, target);

        if (!path.isEmpty()) {
            if (isNearTarget(path)) {
                eat(path.getTargetCoordinates(), map);
            } else {
                changePosition(map, from, path.getStep(speed));
            }
        }else {
            hungerPoints--;
            if (hungerPoints <= 0) {
                health--;
                if (health <= 0) {
                    map.removeEntity(from);
                }
            }
        }
    }

    private boolean isNearTarget(Path path) {
        return path.length() == 1;
    }

    private void changePosition(WorldMap map, Coordinates from, Coordinates to) {
        map.removeEntity(from);
        map.placeEntity(to, this);
    }

    protected abstract void eat(Coordinates coordinates, WorldMap map);

}
