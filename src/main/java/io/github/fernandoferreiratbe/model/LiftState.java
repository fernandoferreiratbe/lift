package io.github.fernandoferreiratbe.model;

public class LiftState {
    private Floor currentFloor;
    private DoorState doorState;
    private int peopleOnTheLift;
    private LiftMovementStatus liftMovementStatus;

    public LiftState(Floor currentFloor, DoorState doorState, LiftMovementStatus liftMovementStatus) {
        this.setCurrentFloor(currentFloor);
        this.setDoorState(doorState);
        this.setLiftMovementAs(liftMovementStatus);
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

    public int getPeopleOnTheLift() {
        return this.peopleOnTheLift;
    }

    public void setPeopleOnTheLift(int peopleOnTheLift) {
        this.peopleOnTheLift = peopleOnTheLift;
    }

    public void setLiftMovementAs(LiftMovementStatus liftMovementStatus) {
        this.liftMovementStatus = liftMovementStatus;
    }

    public LiftMovementStatus getLiftMovementStatus() {
        return this.liftMovementStatus;
    }
}
