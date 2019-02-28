package io.github.fernandoferreiratbe.model;

import io.github.fernandoferreiratbe.controller.Building;
import io.github.fernandoferreiratbe.controller.BuildingRuntimeException;
import io.github.fernandoferreiratbe.controller.IllegalBuildingOperationException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FreightLiftTest {
    // TODO Testar se o elevador está pronto para ir levando em consideração o peso

    @Test
    public void GivenFreightLift_GetMoreThanFourteenPeople_ThrowException() {
        // Arrange
        String EXPECTED_MSG = "Maximum weight for freight lift achieved. So sorry for that.";
        try {
            Building building = new Building();
            building.callLift(LiftType.FREIGHT, LiftDirection.UP);

            // Action
            building.setFloorToGo(1, 7);
            building.setFloorToGo(2, 7);
            building.setFloorToGo(3, 1);
            fail("Test failed. At this point should be throws an exception.");
        } catch (IllegalBuildingOperationException | BuildingRuntimeException exception) {
            // Assert
            Assert.assertEquals(EXPECTED_MSG, exception.getMessage());
        }
    }
}
