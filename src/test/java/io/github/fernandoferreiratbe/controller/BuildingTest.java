package io.github.fernandoferreiratbe.controller;

import io.github.fernandoferreiratbe.model.LiftDirection;
import io.github.fernandoferreiratbe.model.LiftType;
import org.junit.Assert;
import org.junit.Test;

public class BuildingTest {

    @Test
    public void GivenLift_GotMaximumWeight_ThrowsRuntimeException() {
        try {
            // Arrange
            Building building = new Building();
            building.callLift(LiftType.SOCIAL, LiftDirection.UP);

            // Action
            building.setFloorToGo(1, 9);
        } catch (BuildingRuntimeException | IllegalBuildingOperationException exception) {
            // Assert
            Assert.assertEquals("Maximum number of people for social lift achieved. So sorry for that.", exception.getMessage());
        }
    }

    @Test
    public void GivenValidBuilding_SetInvalidDirection_ThrowsException() {
        // Arrange
        Building building = new Building();

        try {
            // Action
            building.callLift(LiftType.SOCIAL, LiftDirection.DOWN);
        } catch (IllegalBuildingOperationException e) {
            // Assert
            Assert.assertEquals("Lift is already on Ground floor.", e.getMessage());
        }
    }

    @Test
    public void GivenValidBuilding_SetFloorBeforeCallLift_ThrowsException() {
        try {
            // Arrange
            Building building = new Building();

            // Action
            building.setFloorToGo(1, 0);
        } catch (BuildingRuntimeException exception) {
            // Assert
            Assert.assertEquals("Please call lift before.", exception.getMessage());
        }
    }
}
