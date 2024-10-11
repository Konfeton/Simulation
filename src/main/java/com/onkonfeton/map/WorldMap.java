package com.onkonfeton.map;

import com.onkonfeton.entity.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public Set<Coordinates> getCoordinatesOfEntityType(Class<? extends Entity> clazz) {
        Set<Coordinates> result = new HashSet<>();

        for (Map.Entry<Coordinates, Entity> entry : this.entities.entrySet()) {
            if (clazz.isInstance(entry.getValue())) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public void removeEntity(Coordinates from){
        Entity entity = entities.remove(from);
        if (entity == null){
            throw new IllegalArgumentException("Tried to remove non-existent entity by coordinates: +" + from);
        }
    }

    public void placeEntity(Coordinates to, Entity entity){
        if (!isValidCoordinates(to)) {
            throw new IllegalArgumentException("Cannot place entity by coordinates " + to);
        }
        entities.put(to, entity);
    }

    private boolean isValidCoordinates(Coordinates coordinates) {
        return isCoordinatesInRange(coordinates) && isSquareEmpty(coordinates);
    }

    private boolean isCoordinatesInRange(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return (x >= 0 && x < maxWorldX) && (y >= 0 && y < maxWorldY);
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
