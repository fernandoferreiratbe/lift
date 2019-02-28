package io.github.fernandoferreiratbe.model;

import io.github.fernandoferreiratbe.controller.Building;
import io.github.fernandoferreiratbe.controller.BuildingRuntimeException;
import io.github.fernandoferreiratbe.controller.IllegalBuildingOperationException;
import org.junit.Assert;
import org.junit.Test;

public class SocialLiftTest {
    // TODO testar exception para quando não tiver nenhum piso selecionado e for acionado o Go

    @Test
    public void GivenSocialLift_GetMoreThanEightPeople_ThrowException() {
        // Arrange
        String EXPECTED_MSG = "Maximum number of people for social lift achieved. So sorry for that.";
        try {
            Building building = new Building();
            building.callLift(LiftType.SOCIAL, LiftDirection.UP);

            // Action
            building.setFloorToGo(1, 9);
        } catch (IllegalBuildingOperationException | BuildingRuntimeException exception) {
            // Assert
            Assert.assertEquals(EXPECTED_MSG, exception.getMessage());
        }
    }
}
