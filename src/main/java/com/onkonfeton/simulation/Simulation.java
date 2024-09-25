package com.onkonfeton.simulation;

import com.onkonfeton.action.Action;
import com.onkonfeton.entity.moveable.Herbivore;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.render.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final String END_MESSAGE = "Травоядных больше нет, симуляция окончена";
    public static final String STOP_CONDITION_MESSAGE = "Введите '0' чтобы остановить бесконечную симуляцию";
    public static final int PAUSE_TIME_MS = 1000;

    private final WorldMap map;
    private int turnCounter;
    private final Renderer renderer;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private final SpawnRates spawnRates;
    private boolean isRunning = true;

    public Simulation(WorldMap map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
        spawnRates = new SpawnRates(map);
//        initializeActions();
        doActions(initActions);
    }

//    private void initializeActions() {
//        initActions.add(new EntitySpawnAction((spawnRates.getNumberToSpawn(Grass.class)), Grass::new));
//        initActions.add(new EntitySpawnAction(spawnRates.getTreeSpawnRate(), Tree::new));
//        initActions.add(new EntitySpawnAction(spawnRates.getRockSpawnRate(), Rock::new));
//        initActions.add(new EntitySpawnAction(spawnRates.getCarrotSpawnRate(), Carrot::new));
//        initActions.add(new EntitySpawnAction(spawnRates.getHerbivoreSpawnRate(), Herbivore::new));
//        initActions.add(new EntitySpawnAction(spawnRates.getPredatorSpawnRate(), Predator::new));
//
//        turnActions.add(new MakeMoveAction());
//        turnActions.add(new DynamicEntitySpawnAction(spawnRates.getCarrotSpawnRate(), Carrot::new));
//        turnActions.add(new DynamicEntitySpawnAction(spawnRates.getGrassSpawnRate(), Grass::new));
//    }

    private void doActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform(map);
        }
    }

    public void startInfinite() {
        isRunning = true;
        while(isRunning){
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
            isRunning = false;
        }
    }

    public boolean isOver() {
        return map.getEntitiesOfType(Herbivore.class).keySet().isEmpty();
    }
}
