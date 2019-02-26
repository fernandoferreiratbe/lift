package io.github.fernandoferreiratbe.model;

public class Floor {
    private int floor;
    private FloorState isThisFloorVisited;
    private int howManyPeople;
    private int people;

    public Floor(int floor) {
        this.floor = floor;
        this.isThisFloorVisited = FloorState.NOT_VISITED;
    }

    public Floor(int floor, int howManyPeople) {
        this.floor = floor;
        this.howManyPeople = howManyPeople;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setState(FloorState isThisFloorVisited) {
        this.isThisFloorVisited = isThisFloorVisited;
    }

    public FloorState isVisited() {
        return this.isThisFloorVisited;
    }

    public int getHowManyPeople() {
        return this.howManyPeople;
    }

    public void setPeople(int people) {
        this.howManyPeople += people;
    }
}
