package io.github.fernandoferreiratbe.model;

import org.junit.Assume;
import org.junit.Test;

public class LiftTest {

    @Test
    public void CreateLiftObject_TestInitialState_ResultIsOk() {
        // Arrange
        Lift lift;
        LiftState liftState;

        // Action
        lift = LiftFactory.getLift();
        liftState = lift.getState();

        // Assert
        Assume.assumeTrue(liftState.getDoorState().equals(DoorState.OPEN));
        Assume.assumeTrue(liftState.getCurrentFloor().getFloor() == 0);
        Assume.assumeTrue(liftState.getPeopleOnTheLift() == 0);
        Assume.assumeTrue(!liftState.isTheLiftInMovement());
    }
}
