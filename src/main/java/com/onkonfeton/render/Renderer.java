package com.onkonfeton.render;

import com.onkonfeton.map.WorldMap;

public interface Renderer {
    void render (WorldMap map, int turnCounter);

    void renderStopCondition(String stopCondition);
}
