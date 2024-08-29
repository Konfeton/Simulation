package com.onkonfeton.action;

import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.moveable.Creature;

import java.util.Set;

public class MakeMoveAction extends Action{
    @Override
    public void perform(WorldMap map) {
        //todo: rework, retrieve coordinates, because when Predator eats Herbivore then Herbivore still in this map
        Set<Coordinates> coordinates = map.getEntitiesOfType(Creature.class).keySet();
        for (Coordinates coordinate : coordinates) {
            if (!map.isSquareEmpty(coordinate)){
                Creature creature = (Creature) map.getEntityByCoordinates(coordinate);
                creature.makeMove(coordinate,map);
            }
        }
    }
}
