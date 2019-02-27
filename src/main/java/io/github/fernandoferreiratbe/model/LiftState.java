package io.github.fernandoferreiratbe.model;

public class LiftState {
    private Floor currentFloor;
    private DoorState doorState;
    private int peopleOnTheLift;
    private boolean isTheLiftInMovement;

    public LiftState(Floor currentFloor, DoorState doorState, int peopleOnTheLift, boolean isTheLiftInMovement) {
        this.currentFloor = currentFloor;
        this.doorState = doorState;
        this.peopleOnTheLift = peopleOnTheLift;
        this.isTheLiftInMovement = isTheLiftInMovement;
    }

    public DoorState getDoorState() {
        return this.doorState;
    }

    public void setLiftInMovement(boolean liftInMovement) {
        this.isTheLiftInMovement = liftInMovement;
    }

    public Floor getCurrentFloor() {
        return this.currentFloor;
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    public int getPeopleOnTheLift() {
        return this.peopleOnTheLift;
    }

    public boolean isTheLiftInMovement() {
        return this.isTheLiftInMovement;
    }
}
