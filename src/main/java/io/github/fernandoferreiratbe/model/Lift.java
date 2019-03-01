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
        int INITIAL_PEOPLE_QUANTITY = 0;

        this.liftState =
                new LiftState(new Floor(GROUND_FLOOR, INITIAL_PEOPLE_QUANTITY), DoorState.OPEN, LiftMovementStatus.STOPPED);
    }

    public LiftDirection getDirection() {
        return this.direction;
    }

    public void setDirection(LiftDirection direction) throws IllegalLiftOperationException {
        if (this.getState().getCurrentFloor().getFloor() == 0 && direction.equals(LiftDirection.DOWN)) {
            throw new IllegalLiftOperationException("Lift is already on Ground floor.");
        }
        this.direction = direction;
    }

    public List<Floor> getBuildingFloors() {
        return this.buildingFloors;
    }

    public void setBuildingFloors(List<Floor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    @Override
    public LiftState getState() {
        return this.liftState;
    }

    @Override
    public void closeTheDoor() {
        this.liftState.setDoorState(DoorState.CLOSED);
    }

    @Override
    public void openTheDoor() {
        this.liftState.setDoorState(DoorState.OPEN);
    }

    /**
     *
     * @param floor
     * @throws IllegalLiftStateException
     *
     * If there is a request from other person to go at the same floor, just add that new person to floor.
     */
    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        if (this.floorsToGo.contains(floor)) {
            for(Floor f : this.floorsToGo) {
                if (f.getFloor() == floor.getFloor()) {
                    f.addPeopleToStopOnThisFloor(floor.getPeopleOnThisFloor());
                    break;
                }
            }
        } else {
            this.floorsToGo.add(floor);
        }

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
    
    @Override
    public void clearFloorsToGoHistory() {
        this.floorsToGo.clear();
    }

    @Override
    public void setLiftMovementAs(LiftMovementStatus liftMovement) {
        this.liftState.setLiftMovementAs(liftMovement);
    }

    @Override
    public LiftMovementStatus getLiftMovementStatus() {
        return this.liftState.getLiftMovementStatus();
    }
}
