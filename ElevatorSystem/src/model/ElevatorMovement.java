package model;

import java.time.LocalDateTime;

public class ElevatorMovement {
    private Elevator elevator;
    private String description;
    private LocalDateTime time;

    public ElevatorMovement(Elevator elevator, String description, LocalDateTime time) {
        this.elevator = elevator;
        this.description = description;
        this.time = time;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ElevatorMovement{" +
                "elevator=" + elevator +
                ", description='" + description + '\'' +
                ", time=" + time +
                "}\n";
    }
}