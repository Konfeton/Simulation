package com.onkonfeton.action;

import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.Random;
import java.util.function.Supplier;

public class EntitySpawnAction extends Action{
    protected final int numberToSpawn;
    protected final Supplier<? extends Entity> entitySupplier;

    public EntitySpawnAction(int numberToSpawn, Supplier<? extends Entity> entitySupplier) {
        this.numberToSpawn = numberToSpawn;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public void perform(WorldMap map) {
        int numberToSpawn = getNumberToSpawn(map);
        for (int i = 0; i <= numberToSpawn; i++) {
            Coordinates coordinates = getRandomEmptyCoordinates(map);
            map.placeEntity(coordinates, entitySupplier.get());
        }
    }

    protected int getNumberToSpawn(WorldMap map){
        return numberToSpawn;
    }

    private Coordinates getRandomEmptyCoordinates(WorldMap map) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(map.getMaxWorldX());
            int y = random.nextInt(map.getMaxWorldY());
            Coordinates coordinates = new Coordinates(x, y);
            if (map.isSquareEmpty(coordinates)) {
                return coordinates;
            }
        }
    }
}
