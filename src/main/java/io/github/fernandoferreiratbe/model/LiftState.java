package io.github.fernandoferreiratbe.model;

public class LiftState {
    private Floor currentFloor;
    private DoorState doorState;
    private int peopleOnTheLift;
    private boolean isTheLiftInMovement;

    public LiftState(Floor currentFloor, DoorState doorState, boolean isTheLiftInMovement) {
        this.setCurrentFloor(currentFloor);
        this.setDoorState(doorState);
        this.setLiftInMovement(isTheLiftInMovement);
    }

    public Floor getCurrentFloor() {
        return this.currentFloor;
    }

    public void setCurrentFloor(Floor floor){
        this.currentFloor = floor;
    }

    public DoorState getDoorState() {
        return this.doorState;
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    public boolean isTheLiftInMovement() {
        return this.isTheLiftInMovement;
    }

    public void setLiftInMovement(boolean liftInMovement) {
        this.isTheLiftInMovement = liftInMovement;
    }

    public int getPeopleOnTheLift() {
        return this.peopleOnTheLift;
    }

    public void setPeopleOnTheLift(int peopleOnTheLift) {
        this.peopleOnTheLift = peopleOnTheLift;
    }
}
