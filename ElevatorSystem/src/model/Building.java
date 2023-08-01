package model;

public class Building {
    private String name;
    private int floorCount;
    private Floor[] floorArray;

    public Building(String name, int floorCount) {
        this.name = name;
        this.floorCount = floorCount;
        floorArray = new Floor[floorCount];

        for (int i = 0; i < floorCount; i++) {
            floorArray[i] = new Floor(i + ". Floor", i);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public Floor[] getFloorArray() {
        return floorArray;
    }

    public void setFloorArray(Floor[] floorArray) {
        this.floorArray = floorArray;
    }
}
