package model;

public class ElevatorDoor {
    private String name;
    private Elevator elevator;

    public void openDoor(){
        System.out.println(elevator.getName() + " opened at " + elevator.getCurrentFloorId());
    }

    public void closeDoor(){
        System.out.println(elevator.getName() + " closed at " + elevator.getCurrentFloorId());
    }

    public void showElevatorInfo(){
        System.out.print(elevator.getName() + " at: " + elevator.getCurrentFloorId() + " ");

        if(elevator.getMovementDirection() > 0){
            System.out.println("Move: UP");
        }else if(elevator.getMovementDirection() < 0){
            System.out.println("Move: DOWN");
        }else{
            System.out.println("");
        }
    }
}