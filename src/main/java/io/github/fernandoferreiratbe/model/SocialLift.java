package io.github.fernandoferreiratbe.model;

public class SocialLift extends Lift {

    private static final int MAX_NUMBER_PEOPLE = 8;

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getPeopleOnThisFloor()) > SocialLift.MAX_NUMBER_PEOPLE) {
            throw new IllegalLiftStateException("Maximum number of people for social lift achieved. So sorry for that.");
        }

        super.setFloorToGo(floor);
    }

    @Override
    public void go() throws IllegalLiftStateException {
        super.go();
    }
}
