package com.onkonfeton.action;

import com.onkonfeton.entity.stationary.Tree;

public class TreeGenerateAction extends SpawnAction<Tree>{

    private static final float DEFAULT_SPAWN_RATE = 0.03f;

    public TreeGenerateAction() {
        super(DEFAULT_SPAWN_RATE);
    }

    @Override
    protected Tree getEntity() {
        return new Tree();
    }
}
