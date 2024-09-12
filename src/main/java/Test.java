import com.onkonfeton.entity.stationary.Carrot;
import com.onkonfeton.entity.stationary.Grass;
import com.onkonfeton.entity.stationary.HerbivoreFood;
import com.onkonfeton.path.BreadthFirstSearch;
import com.onkonfeton.path.Path;
import com.onkonfeton.path.PathFinder;
import com.onkonfeton.render.ConsoleRenderer;
import com.onkonfeton.Coordinates;
import com.onkonfeton.Simulation;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.moveable.Herbivore;
import com.onkonfeton.entity.stationary.Rock;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(6, 6);
        Coordinates coordinates = new Coordinates(1, 4);
        map.placeEntity(coordinates, new Herbivore());
        map.placeEntity(new Coordinates(1, 2), new Grass());

        PathFinder pathFinder = new BreadthFirstSearch(HerbivoreFood.class, map);
        Path path = pathFinder.findPath(coordinates);
    }
}
