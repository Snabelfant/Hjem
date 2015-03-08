package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class StopPoint {
    private int id;
    private String name;
    private int x;
    private int y;

    public StopPoint(@JsonProperty("ID") int id,
                     @JsonProperty("Name") String name,
                     @JsonProperty("X") int x,
                     @JsonProperty("Y") int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

}
