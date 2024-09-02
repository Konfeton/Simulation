import com.onkonfeton.ConsoleRenderer;
import com.onkonfeton.Coordinates;
import com.onkonfeton.Simulation;
import com.onkonfeton.WorldMap;
import com.onkonfeton.entity.moveable.Herbivore;
import com.onkonfeton.entity.stationary.Rock;
import com.onkonfeton.path.Path;

public class Test {
    public static void main(String[] args) {
        ConsoleRenderer renderer = new ConsoleRenderer();
        WorldMap map = new WorldMap(10, 10);
        Simulation simulation = new Simulation(
                map,
                renderer);
        map.placeEntity(new Coordinates(0,0), new Herbivore());
        map.placeEntity(new Coordinates(1,0), new Rock());
        map.placeEntity(new Coordinates(0,1), new Rock());
        simulation.startInfinite();
    }
}
