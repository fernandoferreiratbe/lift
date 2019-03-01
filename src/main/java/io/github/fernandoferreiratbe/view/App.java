package io.github.fernandoferreiratbe.view;

import io.github.fernandoferreiratbe.controller.Building;
import io.github.fernandoferreiratbe.controller.BuildingRuntimeException;
import io.github.fernandoferreiratbe.controller.IllegalBuildingOperationException;
import io.github.fernandoferreiratbe.model.LiftDirection;
import io.github.fernandoferreiratbe.model.LiftType;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws BuildingRuntimeException {
        Scanner scanner = new Scanner(System.in);
        Integer option;
        Integer floorNumber;
        Integer people;

        System.out.println("****************************************");
        System.out.println("**     Welcome to the Solar Tower     **");
        System.out.println("**                                    **");
        System.out.println("** Choose the lift:                   **");
        System.out.println("** 1 - For Social Lift                **");
        System.out.println("** 2 - For Freight Lift               **");

        try {
            do {
                option = scanner.nextInt();
                if (option != 1 && option != 2) {
                    System.out.println("Invalid lift option. Type another one.");
                }
            } while(!option.equals(1) && !option.equals(2));

            Building building = new Building();
            LiftType liftType = (option.equals(1)) ? LiftType.SOCIAL : LiftType.FREIGHT;

            building.callLift(liftType, LiftDirection.UP);

            do {
                do {
                    System.out.println("** Which floor would you like to go? **");
                    floorNumber = scanner.nextInt();

                    if (floorNumber < 1 || floorNumber > 30) {
                        System.out.println("Invalid floor. Choose another one.");
                    }
                } while (floorNumber < 1 ||  floorNumber > 30);

                do {
                    System.out.println("** How many people are going to this floor? **");
                    people = scanner.nextInt();

                    if (people < 1) {
                        System.out.println("Invalid number of people. Type another one.");
                    }
                } while (people < 1);

                building.setFloorToGo(floorNumber, people);

                if (liftType.equals(LiftType.FREIGHT)) {
                    break;
                }
            } while (App.isThereAnyoneElse(scanner));

            building.go();

            System.out.println("Lift is on the Ground Floor.");
        } catch (BuildingRuntimeException | IllegalBuildingOperationException e) {
            System.out.println("Occurs the following problem:  " + e.getMessage());
            System.out.println("Please, call lift again.");
        }
    }

    private static boolean isThereAnyoneElse(Scanner scanner) {
        String response;

        do {
            System.out.println("** Is there anyone else to go? **");
            System.out.println("** Y [Yes] or N [No]**");

            response = scanner.next();

        } while (!response.toUpperCase().equals("Y") && !response.toUpperCase().equals("N"));

        return response.toUpperCase().equals("Y");
    }

    public static void talkToUser(String message) {
        System.out.println(message);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
