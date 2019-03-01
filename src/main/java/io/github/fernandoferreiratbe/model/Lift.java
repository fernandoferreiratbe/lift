package io.github.fernandoferreiratbe.model;

import io.github.fernandoferreiratbe.view.App;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Lift implements ILift {
    private List<Floor> floorsToGo = new ArrayList<>();
    private LiftState liftState;
    private LiftDirection direction;
    private List<Floor> buildingFloors;
    private int nextBuildingFloorToVisit = 1;

    public Lift() {
        int GROUND_FLOOR = 0;
        int INITIAL_PEOPLE_QUANTITY = 0;
        boolean INITIAL_MOVEMENT_STATE = false;

        this.liftState = new LiftState(
                new Floor(GROUND_FLOOR, INITIAL_PEOPLE_QUANTITY),
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

    public List<Floor> getBuildingFloors() {
        return this.buildingFloors;
    }

    public void setBuildingFloors(List<Floor> buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    @Override
    public void closeTheDoor() {
        this.liftState.setDoorState(DoorState.CLOSED);
    }

    @Override
    public void openTheDoor() {
        this.liftState.setDoorState(DoorState.OPEN);
    }

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

    @Override
    public void go() {
        this.getState().setLiftInMovement(true);

        for (Floor floorToGo : this.getFloorsToGo()) {
            for (; this.nextBuildingFloorToVisit <= this.getBuildingFloors().size(); this.nextBuildingFloorToVisit++) {

                App.talkToUser("FLOOR [ " + this.nextBuildingFloorToVisit + " ]");
                Floor buildingFloor = this.getBuildingFloors().get(this.nextBuildingFloorToVisit);

                if (floorToGo.getFloor() == buildingFloor.getFloor()) {
                    App.talkToUser("Stopping Lift at floor " + buildingFloor.getFloor());
                    this.getState().setLiftInMovement(false);

                    App.talkToUser("Open the door");
                    this.openTheDoor();

                    App.talkToUser("Get out " + floorToGo.getPeopleOnThisFloor() + " from lift.");
                    this.setNextBuildingFloorToVisit(this.nextBuildingFloorToVisit + 1);

                    App.talkToUser("Closing the door.");
                    this.closeTheDoor();

                    App.talkToUser("Lift is going...");
                    this.getState().setLiftInMovement(true);
                    break;
                }
            }
        }

        App.talkToUser("Coming back to ground floor...");

        for (int i = --this.nextBuildingFloorToVisit; i >= 0; i--) {
            App.talkToUser("FLOOR [ " + i + " ]");
        }
    }

    private void setNextBuildingFloorToVisit(int nextBuildingFloorToVisit) {
        this.nextBuildingFloorToVisit = nextBuildingFloorToVisit;
    }

    private int countPeopleOnTheLift() {
        return this.floorsToGo
                .stream()
                .mapToInt(Floor::getPeopleOnThisFloor)
                .sum();
    }
}
