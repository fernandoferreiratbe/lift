package io.github.fernandoferreiratbe.model;

import org.junit.Assume;
import org.junit.Test;

public class LiftFactoryTest {

    @Test
    public void CallCorrectMethod_FactoryCreateLift_ResultIsOK() {
        // Arrange
        Lift lift;

        // Action
        lift = LiftFactory.getLift();

        // Assert
        Assume.assumeNotNull(lift);
    }
}
