package io.github.fernandoferreiratbe.model;

import java.util.List;

public interface ILift {

    void setFloorToGo(Floor floor) throws IllegalLiftStateException;

    List<Floor> getFloorsToGo();

    void setDirection(LiftDirection liftDirection) throws IllegalLiftOperationException;

    void go();

    LiftState getState();

    void setBuildingFloors(List<Floor> buildingFloors);

    boolean isReadyToGo() throws IllegalLiftStateException;

    void closeTheDoor();
}
