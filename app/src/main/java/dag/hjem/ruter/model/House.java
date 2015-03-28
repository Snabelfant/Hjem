package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Dag on 07.03.2015.
 */
public class House {
    private int x;
    private int y;
    private String name;

    public House(@JsonProperty("X") int x,
                 @JsonProperty("Y") int y,
                 @JsonProperty("Name") String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
