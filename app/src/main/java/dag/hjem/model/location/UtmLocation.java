package dag.hjem.model.location;


import org.codehaus.jackson.annotate.JsonProperty;

import dag.hjem.gps.UtmPosition;

/**
 * Created by Dag on 22.02.2015.
 */
public class UtmLocation extends Location {
    private int x;
    private int y;


    public UtmLocation(@JsonProperty("Name") String name, @JsonProperty("X") int x, @JsonProperty("Y") int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return "UtmLocation{" +
                "name=" + getName() +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
