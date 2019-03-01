package io.github.fernandoferreiratbe.model;

import org.junit.Assert;
import org.junit.Test;

public class LiftTest {

    @Test
    public void CreateLiftObject_TestInitialState_ResultIsOk() {
        // Arrange
        Lift lift;
        LiftState liftState;
        int GROUND_FLOOR = 0;
        int PEOPLE_ON_THE_LIFT = 0;

        // Action
        lift = new LiftFactory().getLift(LiftType.SOCIAL);
        liftState = lift.getState();

        // Assert
        Assert.assertEquals(liftState.getDoorState(), DoorState.OPEN);
        Assert.assertEquals(liftState.getCurrentFloor().getFloor(), GROUND_FLOOR);
        Assert.assertEquals(liftState.getPeopleOnTheLift(), PEOPLE_ON_THE_LIFT);
        Assert.assertEquals(liftState.getLiftMovementStatus(), LiftMovementStatus.STOPPED);
    }

    @Test
    public void GivenLift_LiftHasNoFloorToGo_ThrowsException() {
        try {
            // Arrange
            ILift lift = new LiftFactory().getLift(LiftType.SOCIAL);

            // Action
            lift.closeTheDoor();
            lift.isReadyToGo();

        } catch (IllegalLiftStateException exception) {
            // Assert
            Assert.assertEquals("There is no floor to visit.", exception.getMessage());
        }
    }

    @Test
    public void GivenLift_TheDoorIsOpen_ThrowsException() {
        try {
            // Arrange
            ILift lift = new LiftFactory().getLift(LiftType.SOCIAL);
            Floor floor = new Floor(1, 2);

            // Action
            lift.setDirection(LiftDirection.UP);
            lift.setFloorToGo(floor);
            lift.isReadyToGo();
        } catch (IllegalLiftStateException exception) {
            // Assert
            Assert.assertEquals("Lift door is open", exception.getMessage());
        } catch (IllegalLiftOperationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenLift_LiftHasAllParametersDefined_ResultIsOk() {
        try {
            // Arrange
            ILift lift = new LiftFactory().getLift(LiftType.SOCIAL);
            Floor floor = new Floor(1, 2);

            // Action
            lift.setDirection(LiftDirection.UP);
            lift.setFloorToGo(floor);
            lift.closeTheDoor();

            // Assert
            Assert.assertTrue(lift.isReadyToGo());
        } catch (IllegalLiftStateException | IllegalLiftOperationException e) {
            e.printStackTrace();
        }
    }
}
