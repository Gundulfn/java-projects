package model;

import java.time.LocalDateTime;
import java.util.*;

public class Elevator {
    private String name;
    private int capacity;
    private ElevatorDoor elevatorDoor;
    private boolean isAvailable;
    private int currentFloorId;
    private Set<Integer> targetFloorIdSet = new TreeSet<>((x, y) -> x.compareTo(y));
    private int currentTargetFloorId;
    private int movementDirection;
    private ControlPanel controlPanel;

    public Elevator(String name, int capacity, int floorCount) {
        this.name = name;
        this.capacity = capacity;
        this.elevatorDoor = new ElevatorDoor();
        this.isAvailable = true;
        this.controlPanel = new ControlPanel(this, floorCount);
    }

    // Custom functions
    public void stop() {
        targetFloorIdSet = new TreeSet<>();
        isAvailable = false;
    }

    public void addTargetFloorId(int floorId) {
        targetFloorIdSet.add(floorId);
    }

    private void moveToFloor() {
        System.out.print(Thread.currentThread().getName() + " ");

        currentTargetFloorId = targetFloorIdSet.stream().toList().get(0);
        movementDirection = (currentTargetFloorId - currentFloorId > 0 ? 1 : -1);
        if (currentFloorId != currentTargetFloorId) {
            isAvailable = false;

            System.out.println(name + " moves to " + currentTargetFloorId + " from " + currentFloorId);
            currentFloorId += movementDirection;

            // Movement delay
            try {
                Thread.sleep(1000);
                moveToFloor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(name + " has arrived to " + currentTargetFloorId);
            targetFloorIdSet.remove(currentFloorId);
            isAvailable = true;

            ElevatorSimulation.addElevatorMovement(this, "Elevator Arrived to " + currentTargetFloorId, LocalDateTime.now());
        }
    }

    public void moveToFloor(int floorId) {
        targetFloorIdSet.add(floorId);
        ElevatorSimulation.addElevatorMovement(this,
                "Elevator Call from " + currentFloorId + " to " + floorId, LocalDateTime.now());

        moveToFloor();
    }

    // Get-Set functions
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ElevatorDoor getElevatorDoor() {
        return elevatorDoor;
    }

    public void setElevatorDoor(ElevatorDoor elevatorDoor) {
        this.elevatorDoor = elevatorDoor;
    }

    public int getMovementDirection() {
        return movementDirection;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCurrentFloorId() {
        return currentFloorId;
    }

    public void setCurrentFloorId(int currentFloorId) {
        this.currentFloorId = currentFloorId;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "name='" + name + '\'' +
                ", isAvailable=" + isAvailable +
                ", currentFloorId=" + currentFloorId +
                '}';
    }
}