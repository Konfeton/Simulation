package com.onkonfeton.path;


import com.onkonfeton.Coordinates;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.Entity;

import java.util.*;

public class BreadthFirstSearch implements PathFinder {


    private final Class<? extends Entity> target;
    private final WorldMap map;
    private final Queue<Coordinates> queue = new ArrayDeque<>();
    private final Map<Coordinates, Coordinates> parentChildMap = new HashMap<>();


    public BreadthFirstSearch(Class<? extends Entity> target, WorldMap map) {
        this.target = target;
        this.map = map;
    }

    public Path findPath(Coordinates start) {
        queue.add(start);

        while(!queue.isEmpty()){
            Coordinates current = queue.remove();

            if (!isEmptySquare(current)){
                if (isTarget(current)){
                    return backtracePath(parentChildMap, start, current);
                }
            }

            List<Coordinates> neighbours = getNeighbours(current);
            for (Coordinates neighbour : neighbours) {
                if(isValidCoordinates(neighbour) && !parentChildMap.containsKey(neighbour)){
                    parentChildMap.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }
        return Path.emptyPath();

    }

    private Path backtracePath(Map<Coordinates, Coordinates> parent, Coordinates start, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
        path.add(end);
        while(!path.getLast().equals(start)){
            path.add(parent.get(path.getLast()));
        }

        Collections.reverse(path);
        return new Path(path);

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

    private boolean isValidCoordinates(Coordinates coordinates) {
        return isCoordinatesInBorder(coordinates) && (isEmptySquare(coordinates) || isTarget(coordinates));
    }

    private boolean isCoordinatesInBorder(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return x >= 0 && x < map.getMaxWorldX() && y >= 0 && y < map.getMaxWorldY();
    }

    private boolean isEmptySquare(Coordinates coordinates) {
        return map.isSquareEmpty(coordinates);
    }

    private boolean isTarget(Coordinates coordinates) {
        return target.isInstance(map.getEntityByCoordinates(coordinates));
    }




}
