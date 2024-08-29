package com.onkonfeton;

import com.onkonfeton.action.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final WorldMap map;
    private int turnCounter;
    private final Renderer renderer;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();


    public Simulation(WorldMap map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
        init();
    }

    public void startInfinite() {
        while(true){
            makeTurn();
            sleep(1000);
        }
    }

    public void makeTurn(){
        for (Action action : turnActions) {
            action.perform(map);
        }
        turnCounter++;
        renderer.render(map);

        System.out.println("Ход номер: " + turnCounter);

    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {

        }
    }


    private void init() {
        initActions.add(new GrassGenerateAction());
        initActions.add(new TreeGenerateAction());
        initActions.add(new RockGenerateAction());
        initActions.add(new CarrotGenerateAction());
        initActions.add(new HerbivoreGenerateAction());
        initActions.add(new PredatorGenerateAction());

        turnActions.add(new MakeMoveAction());
        turnActions.add(new GrassGenerateAction());
        turnActions.add(new CarrotGenerateAction());

        for (Action action : initActions) {
            action.perform(map);
        }
    }
}
