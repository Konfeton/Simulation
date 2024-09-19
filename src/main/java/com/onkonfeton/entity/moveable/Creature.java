package com.onkonfeton.entity.moveable;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;
import com.onkonfeton.path.BreadthFirstSearch;
import com.onkonfeton.path.Path;
import com.onkonfeton.path.PathFinder;

public abstract class Creature extends Entity {
    protected int speed;
    protected int health;
    protected int hungerPoints;
    protected Class<? extends Entity> target;


    public Creature(int speed, int health, int hungerPoints, Class<? extends Entity> target) {
        this.speed = speed;
        this.health = health;
        this.hungerPoints = hungerPoints;
        this.target = target;
    }

    public void makeMove(Coordinates from, WorldMap map) {
        PathFinder pathFinder = new BreadthFirstSearch(target, map);
        Path path = pathFinder.findPath(from);

        if (!path.isEmpty()) {
            if (isNearTarget(path)) {
                eat(path.getTargetCoordinates(), map);
            } else {
                map.makeMove(from, path.getStep(speed));
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

    protected abstract void eat(Coordinates coordinates, WorldMap map);

}
