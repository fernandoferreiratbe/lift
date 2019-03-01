package io.github.fernandoferreiratbe.controller;

import io.github.fernandoferreiratbe.model.*;
import io.github.fernandoferreiratbe.view.App;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private ILift lift;

    public void callLift(LiftType liftType, LiftDirection liftDirection) throws IllegalBuildingOperationException {
        this.lift = new LiftFactory().getLift(liftType);

        try {
            this.lift.setDirection(liftDirection);
        } catch (IllegalLiftOperationException e) {
            throw new IllegalBuildingOperationException(e.getMessage());
        }

        this.defineBuildingFloors();
    }

    public void setFloorToGo(int number, int howManyPeople) throws BuildingRuntimeException {
        if (this.lift == null) {
            throw new BuildingRuntimeException("Please call lift before.");
        }

        try {
            this.lift.setFloorToGo(new Floor(number, howManyPeople));
        } catch (IllegalLiftStateException exception) {
            throw new BuildingRuntimeException(exception.getMessage());
        }
    }

    public void go() throws BuildingRuntimeException {
        try {
            this.lift.go();
        } catch (IllegalLiftStateException e) {
            throw new BuildingRuntimeException(e.getMessage());
        }
    }

    private void defineBuildingFloors() {
        List<Floor> buildingFloors = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            buildingFloors.add(new Floor(i));
        }

        this.lift.setBuildingFloors(buildingFloors);
    }
}
