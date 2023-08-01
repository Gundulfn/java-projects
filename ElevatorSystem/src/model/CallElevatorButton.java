package model;

public class CallElevatorButton implements IButton {
    private ElevatorBuilding elevatorBuilding;
    private int floorId;

    public CallElevatorButton(ElevatorBuilding elevatorBuilding, int floorId) {
        this.elevatorBuilding = elevatorBuilding;
        this.floorId = floorId;
    }

    @Override
    public void press() {
        System.out.println("Elevator is called");
        elevatorBuilding.callElevator(floorId);
    }
}
