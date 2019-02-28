package io.github.fernandoferreiratbe.model;

import org.junit.Assume;
import org.junit.Test;

public class LiftFactoryTest {

    @Test
    public void CallGetLiftMethod_FactoryCreateLift_ResultIsOk() {
        // Arrange
        Lift lift;

        // Action
        lift = new LiftFactory().getLift(LiftType.SOCIAL);

        // Assert
        Assume.assumeNotNull(lift);
    }
}
