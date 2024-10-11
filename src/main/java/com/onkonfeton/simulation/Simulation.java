package com.onkonfeton.simulation;

import com.onkonfeton.action.Action;
import com.onkonfeton.action.DynamicEntitySpawnAction;
import com.onkonfeton.action.EntitySpawnAction;
import com.onkonfeton.action.MakeMoveAction;
import com.onkonfeton.entity.creature.Herbivore;
import com.onkonfeton.entity.stationary.Grass;
import com.onkonfeton.entity.stationary.Carrot;
import com.onkonfeton.entity.stationary.Tree;
import com.onkonfeton.entity.stationary.Rock;
import com.onkonfeton.entity.creature.Predator;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.render.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final String END_MESSAGE = "Травоядных больше нет, симуляция окончена";
    public static final String STOP_CONDITION_MESSAGE = "Введите '0' чтобы остановить бесконечную симуляцию";
    public static final int PAUSE_TIME_MS = 1000;

    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private final WorldMap map;
    private final Renderer renderer;
    private final SpawnRates spawnRates;
    private int turnCounter;
    private boolean isContinue;

    public Simulation(WorldMap map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
        spawnRates = new SpawnRates(map);
        initializeActions();
        doActions(initActions);
    }

    private void initializeActions() {
        initActions.add(new EntitySpawnAction((spawnRates.getNumberToSpawn(Grass.class)), Grass::new));
        initActions.add(new EntitySpawnAction(spawnRates.getNumberToSpawn(Tree.class), Tree::new));
        initActions.add(new EntitySpawnAction(spawnRates.getNumberToSpawn(Rock.class), Rock::new));
        initActions.add(new EntitySpawnAction(spawnRates.getNumberToSpawn(Carrot.class), Carrot::new));
        initActions.add(new EntitySpawnAction(spawnRates.getNumberToSpawn(Herbivore.class), Herbivore::new));
        initActions.add(new EntitySpawnAction(spawnRates.getNumberToSpawn(Predator.class), Predator::new));

        turnActions.add(new MakeMoveAction());
        turnActions.add(new DynamicEntitySpawnAction(spawnRates.getNumberToSpawn(Carrot.class), Carrot::new));
        turnActions.add(new DynamicEntitySpawnAction(spawnRates.getNumberToSpawn(Grass.class), Grass::new));
    }

    private void doActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform(map);
        }
    }

    public void startInfinite() {
        isContinue = true;
        while(isContinue){
            if (isOver()){
                System.out.println(END_MESSAGE);
                return;
            }
            makeTurn();
            renderer.renderStopCondition(STOP_CONDITION_MESSAGE);
            sleep(PAUSE_TIME_MS);
        }
    }

    public void makeTurn(){
        doActions(turnActions);
        turnCounter++;
        renderer.render(map, turnCounter);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            isContinue = false;
        }
    }

    public boolean isOver() {
        return map.getCoordinatesOfEntityType(Herbivore.class).isEmpty();
    }
}
