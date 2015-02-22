package dag.hjem.model;

/**
 * Created by Dag on 22.02.2015.
 */
public class UtmPosition {
    private int north;
    private int east;
    private String zone;

    public UtmPosition(String zone, int east, int north) {
        this.north = north;
        this.east = east;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return zone + " " + east + " " + north;
    }

    public int getNorth() {
        return north;
    }

    public int getEast() {
        return east;
    }
}
