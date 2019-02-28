package io.github.fernandoferreiratbe.model;

import java.util.ArrayList;
import java.util.List;

public class FreightLift extends Lift {
    private static final int MAX_WEIGHT = 1000;
    private List<Floor> floorsToGo = new ArrayList<>();

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getHowManyPeople()) > FreightLift.MAX_WEIGHT) {
            throw new IllegalLiftStateException("Maximum weight for freight lift achieved. So sorry for that.");
        }

        super.setFloorToGo(floor);
    }

    @Override
    public void go() {
        System.out.println("Social Lift is going...");
    }

    @Override
    public LiftState getState() {
        return super.getState();
    }

    @Override
    public boolean isReadyToGo() {
        // TODO Falta adicionar a condição de peso para utilizar ou não o elevador.
        return this.getState().getDoorState().equals(DoorState.CLOSED) && super.getFloorsToGo().size() > 0;
    }
}
