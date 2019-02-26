package io.github.fernandoferreiratbe.view;

import io.github.fernandoferreiratbe.controller.Building;
import io.github.fernandoferreiratbe.model.LiftDirection;
import io.github.fernandoferreiratbe.model.LiftType;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        Integer option;

        System.out.println("****************************************");
        System.out.println("**     Welcome to the Solar Tower     **");
        System.out.println("**                                    **");
        System.out.println("** Choose the lift:                   **");
        System.out.println("** 1 - For Social Lift                **");
        System.out.println("** 2 - For Freight Lift               **");

        do {
            option = scanner.nextInt();
        } while(!option.equals(1) && !option.equals(2));

        Building building = new Building();
        LiftType liftType = (option.equals(1)) ? LiftType.SOCIAL : LiftType.FREIGHT;

        building.callLift(liftType, LiftDirection.UP);

        System.out.println("** Which floor would you like to go? **");
        Integer floorNumber = scanner.nextInt();

        System.out.println("** How many people are going to this floor? **");
        Integer people = scanner.nextInt();

        building.setFloorToGo(floorNumber, people);

        building.go();

        System.out.println("Finished main method.");
    }
}
