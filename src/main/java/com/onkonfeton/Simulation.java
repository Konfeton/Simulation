package com.onkonfeton;

import com.onkonfeton.action.Action;
import com.onkonfeton.action.DynamicEntitySpawnAction;
import com.onkonfeton.action.EntitySpawnAction;
import com.onkonfeton.action.MakeMoveAction;
import com.onkonfeton.entity.moveable.Herbivore;
import com.onkonfeton.entity.moveable.Predator;
import com.onkonfeton.entity.stationary.Carrot;
import com.onkonfeton.entity.stationary.Grass;
import com.onkonfeton.entity.stationary.Rock;
import com.onkonfeton.entity.stationary.Tree;
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
    private boolean isRunning = true;


    public Simulation(WorldMap map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
        initializeActions();
        doActions(initActions);
    }

    private void initializeActions() {
        initActions.add(new EntitySpawnAction((convertToNumber(0.06)), Grass::new));
        initActions.add(new EntitySpawnAction(convertToNumber(0.03), Tree::new));
        initActions.add(new EntitySpawnAction(convertToNumber(0.03), Rock::new));
        initActions.add(new EntitySpawnAction(convertToNumber(0.02), Carrot::new));
        initActions.add(new EntitySpawnAction(convertToNumber(0.06), Herbivore::new));
        initActions.add(new EntitySpawnAction(convertToNumber(0.04), Predator::new));

        turnActions.add(new MakeMoveAction());
        turnActions.add(new DynamicEntitySpawnAction(convertToNumber(0.02), Carrot::new));
        turnActions.add(new DynamicEntitySpawnAction(convertToNumber(0.06), Grass::new));
    }

    private int convertToNumber(double spawnRate) {
        int mapSize = map.getMapSize();
        return (int) Math.ceil(mapSize * spawnRate);
    }

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
