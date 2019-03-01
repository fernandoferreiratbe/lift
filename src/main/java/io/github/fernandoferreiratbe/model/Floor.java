package io.github.fernandoferreiratbe.model;

public class Floor implements Comparable<Floor> {
    private int floor;
    private int peopleOnThisFloor;

    public Floor(int floor) {
        this.floor = floor;
    }

    public Floor(int floor, int peopleOnThisFloor) {
        this.floor = floor;
        this.peopleOnThisFloor = peopleOnThisFloor;
    }

    public int getFloor() {
        return this.floor;
    }

    public int getPeopleOnThisFloor() {
        return this.peopleOnThisFloor;
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

    public void addPeopleToStopOnThisFloor(int peopleOnThisFloor) {
        this.peopleOnThisFloor += peopleOnThisFloor;
    }
}
