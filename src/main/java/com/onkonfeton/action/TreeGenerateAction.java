package com.onkonfeton.action;

import com.onkonfeton.entity.stationary.Tree;

public class TreeGenerateAction extends SpawnAction<Tree>{

    public TreeGenerateAction() {
        spawnRate = 0.03f;
    }

    @Override
    protected Tree getEntity() {
        return new Tree();
    }
}
