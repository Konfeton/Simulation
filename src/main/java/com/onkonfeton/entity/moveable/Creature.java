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

    public Creature() {
    }

    public Creature(int speed, int health) {
        this.speed = speed;
        this.health = health;
    }

    public void makeMove(Coordinates from, WorldMap map){
        PathFinder pathFinder = new BreadthFirstSearch(target, map);
        Path path = pathFinder.findPath(from);

        if (path.hasPath() && speed >= path.getNumberOfSteps()){
            eat(path.getLastStep(), map);
        }else if (path.hasPath()){
            Coordinates to = path.getStep(speed);
            map.makeMove(from, to);
        }
    }

    protected abstract void eat(Coordinates coordinates, WorldMap map);

}
