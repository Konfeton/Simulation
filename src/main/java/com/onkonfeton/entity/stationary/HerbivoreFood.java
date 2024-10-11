package com.onkonfeton.entity.stationary;

import com.onkonfeton.entity.Entity;

public abstract class HerbivoreFood extends Entity {
    private final int healingPower;

    public HerbivoreFood(int healingPower) {
        this.healingPower = healingPower;
    }

    public int getHealingPower() {
        return healingPower;
    }
}
