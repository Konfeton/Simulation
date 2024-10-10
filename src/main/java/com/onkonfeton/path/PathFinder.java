package com.onkonfeton.path;

import com.onkonfeton.entity.Entity;
import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;

public interface PathFinder {
    Path findPath(WorldMap map, Coordinates start, Class<? extends Entity> target);
}
