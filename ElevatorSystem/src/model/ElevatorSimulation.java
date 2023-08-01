package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ElevatorSimulation {
    private static List<ElevatorMovement> elevatorMovementList = new ArrayList<>();

    public static void simulate() throws InterruptedException {
        ElevatorBuilding elevatorBuilding = new ElevatorBuilding("Plaza 102",
                12, 5, 3);

        Thread t1 = new Thread(() -> {
            elevatorBuilding.callElevator(12);
        });

        Thread t2 = new Thread(() -> {
            elevatorBuilding.callElevator(1);
        });

        Thread t3 = new Thread(() -> {
            elevatorBuilding.callElevator(6);
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    public static void addElevatorMovement(Elevator elevator, String description, LocalDateTime time) {
        elevatorMovementList.add(new ElevatorMovement(elevator, description, time));
    }

    public static void print(){
        System.out.println(elevatorMovementList);
    }
}
