package model;

public class ControlPanel {
    private FloorOptionButton[] floorOptionButtonArray;
    private ElevatorEmergencyButton elevatorEmergencyButton;
    private ElevatorDoorButton elevatorOpenDoorButton, elevatorCloseDoorButton;

    public ControlPanel(Elevator elevator, int floorOptionButtonCount){
        floorOptionButtonArray = new FloorOptionButton[floorOptionButtonCount];
        for(int i = 0; i < floorOptionButtonCount; i++){
            floorOptionButtonArray[i] = new FloorOptionButton(elevator, i);
        }

        elevatorEmergencyButton = new ElevatorEmergencyButton(elevator);
        elevatorOpenDoorButton = new ElevatorDoorButton(true);
        elevatorCloseDoorButton = new ElevatorDoorButton(false);
    }

    public FloorOptionButton[] getFloorOptionButtonArray() {
        return floorOptionButtonArray;
    }

    public ElevatorEmergencyButton getElevatorEmergencyButton() {
        return elevatorEmergencyButton;
    }

    public ElevatorDoorButton getElevatorOpenDoorButton() {
        return elevatorOpenDoorButton;
    }

    public ElevatorDoorButton getElevatorCloseDoorButton() {
        return elevatorCloseDoorButton;
    }
}
