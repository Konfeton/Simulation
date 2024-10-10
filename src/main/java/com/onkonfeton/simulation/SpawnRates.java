package com.onkonfeton.simulation;

import com.onkonfeton.entity.Entity;
import com.onkonfeton.entity.creature.Herbivore;
import com.onkonfeton.entity.creature.Predator;
import com.onkonfeton.entity.stationary.Carrot;
import com.onkonfeton.entity.stationary.Grass;
import com.onkonfeton.entity.stationary.Rock;
import com.onkonfeton.entity.stationary.Tree;
import com.onkonfeton.map.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class SpawnRates {

    private static final float DEFAULT_CARROT_SPAWN_RATE = 0.02f;
    private static final float DEFAULT_GRASS_SPAWN_RATE = 0.06f;
    private static final float DEFAULT_HERBIVORE_SPAWN_RATE = 0.06f;
    private static final float DEFAULT_PREDATOR_SPAWN_RATE = 0.04f;
    private static final float DEFAULT_ROCK_SPAWN_RATE = 0.03f;
    private static final float DEFAULT_TREE_SPAWN_RATE = 0.03f;

    private final Map<Class<? extends Entity>, Float> spawnRates = new HashMap<>();

    private final WorldMap map;


    public SpawnRates(WorldMap map) {
        this.map = map;
        setSpawnRates();
    }

    private void setSpawnRates() {
        spawnRates.put(Carrot.class, DEFAULT_CARROT_SPAWN_RATE);
        spawnRates.put(Grass.class, DEFAULT_GRASS_SPAWN_RATE);
        spawnRates.put(Herbivore.class, DEFAULT_HERBIVORE_SPAWN_RATE);
        spawnRates.put(Predator.class, DEFAULT_PREDATOR_SPAWN_RATE);
        spawnRates.put(Rock.class, DEFAULT_ROCK_SPAWN_RATE);
        spawnRates.put(Tree.class, DEFAULT_TREE_SPAWN_RATE);
    }


  public int getNumberToSpawn(Class<? extends Entity> entityType){
      Float spawnRate = spawnRates.get(entityType);
      if (spawnRate == null){
          throw new IllegalArgumentException("No entities of type " + entityType + " found");
      }
      return convertToNumber(spawnRate);
  }

    public int convertToNumber(double spawnRate) {
        int mapSize = getMapSize();
        return (int) Math.ceil(mapSize * spawnRate);
    }

    private int getMapSize(){
        int maxWorldX = map.getMaxWorldX();
        int maxWorldY = map.getMaxWorldY();
        return maxWorldX * maxWorldY;
    }
}
