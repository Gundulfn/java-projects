package model;

import java.util.HashMap;
import java.util.Map;

public class ElevatorBuilding extends Building {
    private Map<Elevator, ElevatorDoor[]> elevatorMap;
    private Map<Integer, CallElevatorButton[]> callElevatorButtonMap;

    public ElevatorBuilding(String name, int floorCount, int elevatorCount, int callElevatorButtonCount) {
        super(name, floorCount);

        elevatorMap = new HashMap<>();
        callElevatorButtonMap = new HashMap<>();
        for (int i = 0; i < elevatorCount; i++) {
            Elevator elevator = new Elevator(i + ". Elevator", i, floorCount);
            ElevatorDoor[] elevatorDoorArray = new ElevatorDoor[floorCount];

            for (int j = 0; j < floorCount; j++) {
                elevatorDoorArray[j] = new ElevatorDoor();

                // Initialize call elevator buttons
                if (callElevatorButtonMap.size() <= 0) {
                    CallElevatorButton[] callElevatorButtonArray = new CallElevatorButton[callElevatorButtonCount];
                    for (int k = 0; k < callElevatorButtonCount; k++) {
                        callElevatorButtonArray[k] = new CallElevatorButton(this, j);
                    }

                    callElevatorButtonMap.put(j, callElevatorButtonArray);
                }
            }

            elevatorMap.put(elevator, elevatorDoorArray);
        }
    }

    public void callElevator(int floorId) {
        Elevator availableElevator = null;

        // First search: Get idle elevator at same floor
        if (elevatorMap.keySet().stream()
                .filter(elevator -> elevator.getCurrentFloorId() == floorId).findFirst().isPresent()) {
            availableElevator = elevatorMap.keySet().stream()
                    .filter(elevator -> elevator.getCurrentFloorId() == floorId).findFirst().get();
            availableElevator.moveToFloor(floorId);
            return;
        }

        // Second search: Get idle elevator from anywhere
        for (Elevator elevator : elevatorMap.keySet()) {
            if (elevator.isAvailable()) {
                availableElevator = elevator;
                availableElevator.moveToFloor(floorId);
                return;
            }
        }

        // Final search: Get the closest elevator
        if (elevatorMap.keySet().stream()
                .sorted((elevator1, elevator2) -> elevator2.getCurrentFloorId() - elevator1.getCurrentFloorId())
                .findFirst().isPresent()) {
            availableElevator = elevatorMap.keySet().stream()
                    .sorted((elevator1, elevator2) -> elevator2.getCurrentFloorId() - elevator1.getCurrentFloorId())
                    .findFirst().get();
            availableElevator.moveToFloor(floorId);
        }
    }

    public ElevatorDoor getElevatorDoor(Elevator key, int floorId) {
        return elevatorMap.get(key)[floorId];
    }

    public CallElevatorButton getElevatorCallButton(Integer key, int buttonId) {
        return callElevatorButtonMap.get(key)[buttonId];
    }
}