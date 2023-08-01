package model;

public class ElevatorEmergencyButton implements IButton{
    private Elevator elevator;

    public ElevatorEmergencyButton(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void press() {
        System.err.println("EMERGENCY");
        elevator.stop();
    }
}