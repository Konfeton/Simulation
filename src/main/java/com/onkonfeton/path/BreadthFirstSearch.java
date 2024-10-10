package com.onkonfeton.path;


import com.onkonfeton.map.Coordinates;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.*;

public class BreadthFirstSearch implements PathFinder {

    private final Queue<Coordinates> queue = new ArrayDeque<>();
    private final Map<Coordinates, Coordinates> neigbourCurrentMap = new LinkedHashMap<>();

    public Path findPath(WorldMap map, Coordinates start, Class<? extends Entity> target) {
        queue.clear();
        neigbourCurrentMap.clear();

        queue.add(start);

        while(!queue.isEmpty()){
            Coordinates current = queue.remove();

            if (!isEmptySquare(map, current)){
                if (isTarget(map, current, target)){
                    return backtracePath(neigbourCurrentMap, start, current);
                }
            }

            List<Coordinates> neighbours = getNeighbours(current);
            for (Coordinates neighbour : neighbours) {
                if(isValidCoordinates(map, neighbour, target) && !neigbourCurrentMap.containsKey(neighbour)){
                    neigbourCurrentMap.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }

        return Path.emptyPath();
    }

    private Path backtracePath(Map<Coordinates, Coordinates> neigbourCurrentMap, Coordinates start, Coordinates end) {
        List<Coordinates> steps = new ArrayList<>();
        steps.add(end);

        while(!steps.getLast().equals(start)){
            Coordinates previousCoordinates = neigbourCurrentMap.get(steps.getLast());
            steps.add(previousCoordinates);
        }

        Collections.reverse(steps);

        return new Path(steps);
    }

    private List<Coordinates> getNeighbours(Coordinates current) {
        List<Coordinates> neighbours = new ArrayList<>();

        int x = current.getX();
        int y = current.getY();

       neighbours.add(new Coordinates(x, y - 1)); //up
       neighbours.add(new Coordinates(x+1, y));   //right
       neighbours.add(new Coordinates(x, y + 1)); //bottom
       neighbours.add(new Coordinates(x - 1, y)); //left

        return neighbours;
    }

    private boolean isValidCoordinates(WorldMap map, Coordinates coordinates, Class<? extends Entity> target) {
        return isCoordinatesInBorder(map, coordinates) && (isEmptySquare(map, coordinates) || isTarget(map, coordinates, target));
    }

    private boolean isCoordinatesInBorder(WorldMap map, Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return x >= 0 && x < map.getMaxWorldX() && y >= 0 && y < map.getMaxWorldY();
    }

    private boolean isEmptySquare(WorldMap map, Coordinates coordinates) {
        return map.isSquareEmpty(coordinates);
    }

    private boolean isTarget(WorldMap map, Coordinates coordinates, Class<? extends Entity> target) {
        return target.isInstance(map.getEntityByCoordinates(coordinates));
    }
}
