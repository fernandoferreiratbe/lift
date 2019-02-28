package io.github.fernandoferreiratbe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Lift implements ILift {
    private List<Floor> floorsToGo = new ArrayList<>();
    private LiftState liftState;
    private LiftDirection direction;
    private List<Floor> buildingFloors;

    public Lift() {
        int GROUND_FLOOR = 0;
        int INITIAL_PEOPLE_QUATITY = 0;
        boolean INITIAL_MOVEMENT_STATE = false;

        this.liftState = new LiftState(
                new Floor(GROUND_FLOOR, INITIAL_PEOPLE_QUATITY),
                DoorState.OPEN,
                INITIAL_MOVEMENT_STATE
        );
    }

    public void setDirection(LiftDirection direction) throws IllegalLiftOperationException {
        if (this.getState().getCurrentFloor().getFloor() == 0 && direction.equals(LiftDirection.DOWN)) {
            throw new IllegalLiftOperationException("Lift is already on Ground floor.");
        }
        this.direction = direction;
    }

    public LiftState getState() {
        return this.liftState;
    }

    public void setBuildingFloors(List<Floor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    @Override
    public void closeTheDoor() {
        this.liftState.setDoorState(DoorState.CLOSED);
    }

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        this.floorsToGo.add(floor);
        this.liftState.setPeopleOnTheLift(this.countPeopleOnTheLift());

        Collections.sort(this.floorsToGo);
    }

    @Override
    public List<Floor> getFloorsToGo() {
        return Collections.unmodifiableList(this.floorsToGo);
    }

    @Override
    public boolean isReadyToGo() throws IllegalLiftStateException {
        if (!this.getState().getDoorState().equals(DoorState.CLOSED)) {
            throw new IllegalLiftStateException("Lift door is open");
        }

        if (this.getFloorsToGo().size() < 1) {
            throw new IllegalLiftStateException("There is no floor to visit.");
        }

        return true;
    }

    private int countPeopleOnTheLift() {
        return this.floorsToGo
                .stream()
                .mapToInt(Floor::getPeopleOnThisFloor)
                .sum();
    }
}
