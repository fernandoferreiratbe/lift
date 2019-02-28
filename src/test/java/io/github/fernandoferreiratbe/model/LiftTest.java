package io.github.fernandoferreiratbe.model;

import org.junit.Assert;
import org.junit.Test;

public class LiftTest {

    @Test
    public void CreateLiftObject_TestInitialState_ResultIsOk() {
        // Arrange
        Lift lift;
        LiftState liftState;

        // Action
        lift = new LiftFactory().getLift(LiftType.SOCIAL);
        liftState = lift.getState();

        // Assert
        Assert.assertTrue(liftState.getDoorState().equals(DoorState.OPEN));
        Assert.assertTrue(liftState.getCurrentFloor().getFloor() == 0);
        Assert.assertTrue(liftState.getPeopleOnTheLift() == 0);
        Assert.assertTrue(!liftState.isTheLiftInMovement());
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
        } catch (IllegalLiftStateException e) {
            e.printStackTrace();
        }
    }

    // TODO Se o elevador for invocado para ir para baixo, mesmo estando no terreo lance excecao
}
