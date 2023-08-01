package model;

public class FloorOptionButton implements IButton{
    private Elevator elevator;
    private int floorId;

    public FloorOptionButton(Elevator elevator, int floorId) {
        this.elevator = elevator;
        this.floorId = floorId;
    }

    @Override
    public void press() {
        elevator.addTargetFloorId(floorId);
    }
}
