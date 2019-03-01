package io.github.fernandoferreiratbe.model;

public class LiftFactory {

    public Lift getLift(LiftType liftType) {
        Lift lift;

        if (liftType.equals(LiftType.SOCIAL)) {
            lift = new SocialLift();
        } else {
            lift = new FreightLift();
        }

        lift.setType(liftType);

        return lift;
    }
}
