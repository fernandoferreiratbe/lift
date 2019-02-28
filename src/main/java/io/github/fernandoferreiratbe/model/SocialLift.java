package io.github.fernandoferreiratbe.model;

import io.github.fernandoferreiratbe.controller.BuildingRuntimeException;

public class SocialLift extends Lift {

    public static final int MAX_NUMBER_PEOPLE = 8;

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getHowManyPeople()) > SocialLift.MAX_NUMBER_PEOPLE) {
            throw new IllegalLiftStateException("Maximum number of people for social lift achieved. So sorry for that.");
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
    public boolean isReadyToGo() throws IllegalLiftStateException {
        if (super.getPeopleOnTheLift() > 8) {
            throw new IllegalLiftStateException("Maximum weight achieved.");
        }

        if (super.getFloorsToGo().size() < 1) {
            throw new IllegalLiftStateException("There is no floor to visit.");
        }

        return super.isReadyToGo();
    }
}
