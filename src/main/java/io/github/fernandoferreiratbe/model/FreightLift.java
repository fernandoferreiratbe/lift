package io.github.fernandoferreiratbe.model;

import java.util.ArrayList;
import java.util.List;

public class FreightLift extends Lift {
    private static final int MAX_NUMBER_PEOPLE = 14;
    private List<Floor> floorsToGo = new ArrayList<>();

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getPeopleOnThisFloor()) > FreightLift.MAX_NUMBER_PEOPLE) {
            throw new IllegalLiftStateException("Maximum weight for freight lift achieved. So sorry for that.");
        }

        super.setFloorToGo(floor);
    }

    @Override
    public void go() {
        System.out.println("Freight Lift is going...");
    }

    @Override
    public LiftState getState() {
        return super.getState();
    }
}
