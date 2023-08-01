package model;

public class ElevatorDoorButton implements IButton {
    private boolean isOpenDoorButton;

    public ElevatorDoorButton(boolean isOpenDoorButton) {
        this.isOpenDoorButton = isOpenDoorButton;
    }

    @Override
    public void press() {
        if (isOpenDoorButton) {
            System.out.println("Elevator's door is opening");

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Door is wide open");
        } else {
            System.out.println("Elevator's door is closing");

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Door is closed");
        }
    }
}
