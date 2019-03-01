package io.github.fernandoferreiratbe.model;

import io.github.fernandoferreiratbe.view.App;

public class SocialLift extends Lift {

    private static final int MAX_NUMBER_PEOPLE = 8;
    private int nextBuildingFloorToVisit = 1;

    @Override
    public void setFloorToGo(Floor floor) throws IllegalLiftStateException {
        LiftState liftState = super.getState();

        if ((liftState.getPeopleOnTheLift() + floor.getPeopleOnThisFloor()) > SocialLift.MAX_NUMBER_PEOPLE) {
            throw new IllegalLiftStateException("Maximum number of people for social lift achieved. So sorry for that.");
        }

        super.setFloorToGo(floor);
    }

    @Override
    public void go() {
        App.talkToUser("Social Lift is going...");
        this.getState().setLiftInMovement(true);

        for (Floor floorToGo : super.getFloorsToGo()) {
            for (; this.nextBuildingFloorToVisit <= super.getBuildingFloors().size(); this.nextBuildingFloorToVisit++) {

                App.talkToUser("FLOOR [ " + this.nextBuildingFloorToVisit + " ]");
                Floor buildingFloor = super.getBuildingFloors().get(this.nextBuildingFloorToVisit);

                if (floorToGo.getFloor() == buildingFloor.getFloor()) {
                    App.talkToUser("Stopping Social Lift at floor " + buildingFloor.getFloor());
                    this.getState().setLiftInMovement(false);

                    App.talkToUser("Open the door");
                    this.getState().setDoorState(DoorState.OPEN);

                    App.talkToUser("Get out " + floorToGo.getPeopleOnThisFloor() + " from lift.");
                    this.setNextBuildingFloorToVisit(this.nextBuildingFloorToVisit + 1);

                    App.talkToUser("Closing the door.");
                    super.closeTheDoor();

                    App.talkToUser("Social Lift is going...");
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

    @Override
    public LiftState getState() {
        return super.getState();
    }

    private void setNextBuildingFloorToVisit(int nextBuildingFloorToVisit) {
        this.nextBuildingFloorToVisit = nextBuildingFloorToVisit;
    }
}
