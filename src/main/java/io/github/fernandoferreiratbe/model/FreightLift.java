package io.github.fernandoferreiratbe.model;

public class FreightLift extends Lift {

    private static final int MAX_NUMBER_PEOPLE = 14;

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getPeopleOnThisFloor()) > FreightLift.MAX_NUMBER_PEOPLE) {
            throw new IllegalLiftStateException("Maximum weight for freight lift achieved. So sorry for that.");
        }

        super.setFloorToGo(floor);
    }

    @Override
    public void go() throws IllegalLiftStateException {
       super.go();
    }
}
