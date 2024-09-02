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
    protected Class<? extends Entity> target;
    protected int hungerPoints;

    protected static final int BASE_HUNGER_POINTS = 3;

    public void makeMove(Coordinates from, WorldMap map) {
        PathFinder pathFinder = new BreadthFirstSearch(target, map);
        Path path = pathFinder.findPath(from);

        if (path.hasPath()) {
            if (path.length() == 1) {
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

    protected abstract void eat(Coordinates coordinates, WorldMap map);

}
