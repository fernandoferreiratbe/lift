package io.github.fernandoferreiratbe.model;

import java.util.ArrayList;
import java.util.List;

public class Lift {

    private LiftType type;
    private List<Floor> floors = new ArrayList<>();
    private LiftDirection direction;
    private DoorState doorState;
    private int peopleOnLift;
    private boolean isTheLiftInMovment;

    public Lift() {
        this.doorState = DoorState.OPEN;
        this.peopleOnLift = 0;
        this.isTheLiftInMovment = false;

        this.fillFloor();

        // this.setFloorToGo(new Floor(0, 0));
    }

    public LiftDirection getCurrentDirection() {
        return this.direction;
    }

    public void setType(LiftType type) {
        this.type = type;
    }

    public void setFloorToGo(int floor, int people) {

        if (this.type.equals(LiftType.SOCIAL) && this.getHowManyPeopleThereAreOnLift() > 8
                || this.type.equals(LiftType.FREIGHT) && this.getCurrantFreightWeight() > 1200) {
            throw new IllegalArgumentException("Maximum weight achieved.");
        }

        this.floors.get(floor).setPeople(people);
    }

    public void go() throws Exception {
        try {
            if (this.doorState.equals(DoorState.OPEN)) {

                System.out.println("Closing the door...");
                this.doorState = DoorState.CLOSED;

                Thread.sleep(3000);

                System.out.println("Door is closed.");

                Thread.sleep(1000);

                if (this.direction.equals(LiftDirection.UP)) {

                    System.out.println("Lift is up...");
                    this.isTheLiftInMovment = true;
                    System.out.println("Lift is in movement");
                    Thread.sleep(2000);

                    for (int i = 0; i < this.floors.size(); i++) {
                        System.out.println("[ F " + i + " ]");

                        if (this.floors.get(i).getFloor() == i && this.floors.get(i).getHowManyPeople() > 0) {
                            this.isTheLiftInMovment = false;
                            System.out.println("Lift is Stopping");
                            Thread.sleep(2000);

                            System.out.println("Open The Door...");
                            Thread.sleep(2000);
                            System.out.println("Please get out");
                            this.setFloorStatus(this.floors.get(i));
                            Thread.sleep(2000);
                        }
                    }
                } else {
                    System.out.println("Lift is down...");
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void setFloorStatus(Floor floor) {
        this.floors.get(floor.getFloor()).setState(FloorState.NOT_VISITED);
    }

    public void setDirection(LiftDirection direction) {
        this.direction = direction;
    }

    private int getHowManyPeopleThereAreOnLift() {
        return this.floors.stream().mapToInt(Floor::getHowManyPeople).sum();
    }

    private int getCurrantFreightWeight() {
        return 0;
    }

    private boolean isOkForSocialLift() {
        return this.getHowManyPeopleThereAreOnLift() < 9;
    }

    private void fillFloor() {
        for (int i = 0; i < 31; i++) {
            this.floors.add(new Floor(i));
        }
    }
}
