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
    public void go() throws IllegalLiftStateException {
        App.talkToUser("Closing the door.");
        this.closeTheDoor();

        if (this.isReadyToGo()) {
            App.talkToUser("Social Lift is going...");
            super.setLiftMovementAs(LiftMovementStatus.WALKING);

            for (Floor floorToGo : this.getFloorsToGo()) {
                this.getState().setCurrentFloor(floorToGo);

                for (; this.nextBuildingFloorToVisit <= this.getBuildingFloors().size(); this.nextBuildingFloorToVisit++) {

                    App.talkToUser("FLOOR [ " + this.nextBuildingFloorToVisit + " ]");
                    Floor buildingFloor = this.getBuildingFloors().get(this.nextBuildingFloorToVisit);

                    if (floorToGo.getFloor() == buildingFloor.getFloor()) {
                        App.talkToUser("Stopping Social Lift at floor " + buildingFloor.getFloor());
                        super.setLiftMovementAs(LiftMovementStatus.STOPPED);

                        App.talkToUser("Open the door");
                        this.openTheDoor();

                        App.talkToUser("Get out " + floorToGo.getPeopleOnThisFloor() + " from lift.");
                        this.setNextBuildingFloorToVisit(this.nextBuildingFloorToVisit + 1);

                        App.talkToUser("Closing the door.");
                        this.closeTheDoor();

                        App.talkToUser("Social Lift is going...");
                        this.setLiftMovementAs(LiftMovementStatus.WALKING);
                        break;
                    }
                }
            }

            App.talkToUser("Coming back to ground floor...");
            try {
                this.setDirection(LiftDirection.DOWN);
                this.clearFloorsToGoHistory();
            } catch (IllegalLiftOperationException e) {
                e.printStackTrace();
            }

            for (int i = --this.nextBuildingFloorToVisit; i >= 0; i--) {
                App.talkToUser("FLOOR [ " + i + " ]");
            }
        }
    }

    private void setNextBuildingFloorToVisit(int nextBuildingFloorToVisit) {
        this.nextBuildingFloorToVisit = nextBuildingFloorToVisit;
    }
}
