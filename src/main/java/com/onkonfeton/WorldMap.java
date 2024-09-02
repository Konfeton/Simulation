package com.onkonfeton;

import com.onkonfeton.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    private final int maxWorldX;
    private final int maxWorldY;

    public WorldMap(int maxWorldX, int maxWorldY) {
        this.maxWorldX = maxWorldX;
        this.maxWorldY = maxWorldY;
    }

    public Entity getEntityByCoordinates(Coordinates coordinates) {
        Entity entity = entities.get(coordinates);
        if (entity == null){
            throw new IllegalArgumentException("No entities by Coordinates: " + coordinates);
        }
        return entity;
    }

    public<T> Map<Coordinates, T> getEntitiesOfType(Class<? extends Entity> type){
        Map<Coordinates, T> result = new HashMap<>();

        for (Map.Entry<Coordinates, Entity> entry : this.entities.entrySet()) {
            if (type.isInstance(entry.getValue())){
                result.put(entry.getKey(), (T) entry.getValue());
            }
        }

        return result;
    }

    public void makeMove(Coordinates from, Coordinates to) {
        Entity entity = entities.get(from);
        removeEntity(from);
        placeEntity(to, entity);
    }


    public void removeEntity(Coordinates from){
        entities.remove(from);
    }

    public void placeEntity(Coordinates to, Entity entity){
        entities.put(to, entity);
    }

    public boolean isSquareEmpty(Coordinates coordinates){
        return !entities.containsKey(coordinates);
    }


    public int getMaxWorldX() {
        return maxWorldX;
    }

    public int getMaxWorldY() {
        return maxWorldY;
    }
}
