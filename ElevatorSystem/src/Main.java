import model.ElevatorSimulation;

public class Main {
    public static void main(String[] args) {
        try {
            ElevatorSimulation.simulate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ElevatorSimulation.print();
    }
}
