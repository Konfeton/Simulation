package com.onkonfeton.action;

import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.creature.Creature;

import java.util.Set;

public class MakeMoveAction extends Action{
    @Override
    public void perform(WorldMap map) {
        Set<Coordinates> coordinates = map.getEntitiesOfType(Creature.class).keySet();
        for (Coordinates coordinate : coordinates) {
            if (!map.isSquareEmpty(coordinate)){
                Creature creature = (Creature) map.getEntityByCoordinates(coordinate);
                creature.makeMove(coordinate,map);
            }
        }
    }
}
