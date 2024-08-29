package com.onkonfeton.path;

import com.onkonfeton.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class Path {
    private final List<Coordinates> steps;

    public Path(List<Coordinates> steps) {
        this.steps = new ArrayList<>(steps);
    }

    public Coordinates getStep(int speed){
        if (speed > steps.size()){
            return getLastStep();
        }else {
            return steps.get(speed);
        }
    }

    public static Path emptyPath(){
        return new Path(new ArrayList<>());
    }

    public boolean hasPath(){
        return !steps.isEmpty();
    }

    public int getNumberOfSteps() {
        return steps.size() - 1;
    }

    public Coordinates getLastStep() {
        return steps.getLast();
    }

    @Override
    public String toString() {
        return "Path{" +
                "steps=" + steps +
                '}';
    }
}
