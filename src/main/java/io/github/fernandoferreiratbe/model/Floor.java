package io.github.fernandoferreiratbe.model;

public class Floor implements Comparable<Floor> {
    private int floor;
    private FloorState isThisFloorVisited;
    private int howManyPeople;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Floor floor = (Floor) o;

        return this.getFloor() == floor.getFloor();
    }

    @Override
    public int hashCode() {
        return this.getFloor();
    }

    @Override
    public int compareTo(Floor floor) {
        return this.floor - floor.getFloor();
    }
}
