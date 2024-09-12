package com.onkonfeton.render;

import com.onkonfeton.WorldMap;

public interface Renderer {
    void render (WorldMap map, int turnCounter);

    void renderStopCondition(String stopCondition);
}
