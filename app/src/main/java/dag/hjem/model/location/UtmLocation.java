package dag.hjem.model.location;


import dag.hjem.gps.UtmPosition;

/**
 * Created by Dag on 22.02.2015.
 */
public class UtmLocation extends Location {
    private UtmPosition position;

    public UtmLocation(String name, UtmPosition position) {
        super(name);
        this.position = position;
    }

    public UtmLocation(String name, int x, int y) {
        super(name);
        this.position = new UtmPosition(0, "32V",x,y);
    }

    public int getX() {
        return position.getUtmEast();
    }

    public int getY() {
        return position.getUtmNorth();
    }


}
