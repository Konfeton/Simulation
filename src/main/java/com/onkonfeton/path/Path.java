package com.onkonfeton.path;

import com.onkonfeton.map.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Path {
    private final List<Coordinates> steps;
    private final int length;

    public Path(List<Coordinates> steps) {
        this.steps = new ArrayList<>(steps);
        length = this.steps.size() - 1;
    }

    public Coordinates getStep(int speed){
        if (speed >= length){
            return steps.get(length - 1);
        }
        return steps.get(speed);
    }

    public boolean isEmpty(){
        return steps.isEmpty();
    }

    public int length() {
        return length;
    }

    public Coordinates getTargetCoordinates() {
        return steps.getLast();
    }

    public static Path emptyPath(){
        return new Path(Collections.emptyList());
    }
}
