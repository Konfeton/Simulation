package com.onkonfeton.action;

import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.function.Supplier;

public class DynamicEntitySpawnAction extends EntitySpawnAction{
    public DynamicEntitySpawnAction(int totalNumber, Supplier<? extends Entity> entitySupplier) {
        super(totalNumber, entitySupplier);
    }

    protected int getNumberToSpawn(WorldMap map){
        int currentNumber = map.getEntitiesOfType(entitySupplier.get().getClass()).keySet().size();
        if (currentNumber < numberToSpawn){
            return numberToSpawn - currentNumber;
        }else {
            return 0;
        }
    }
}
