package dag.hjem.ruter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private int x;
    private int y;

    public Location(@JsonProperty("X") int x, @JsonProperty("Y") int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
