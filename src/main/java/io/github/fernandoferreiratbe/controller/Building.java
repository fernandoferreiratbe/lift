package io.github.fernandoferreiratbe.controller;

import io.github.fernandoferreiratbe.model.*;

public class Building {
    private Lift lift;

    public void callLift(LiftType liftType, LiftDirection liftDirection) {
        this.lift = LiftFactory.getLift();

        this.lift.setType(liftType);
        this.lift.setDirection(liftDirection);
    }

    public void setFloorToGo(int number, int howManyPeople) {
        this.lift.setFloorToGo(number, howManyPeople);
    }

    public void go() {
        try {
            this.lift.go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
