package io.github.fernandoferreiratbe.model;

public class LiftFactory {

    public Lift getLift(LiftType liftType) {

        if (liftType.equals(LiftType.SOCIAL)) {
            return new SocialLift();
        }

        return new FreightLift();
    }
}
