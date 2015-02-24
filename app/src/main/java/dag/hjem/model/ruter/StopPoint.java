package dag.hjem.model.ruter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopPoint {
    private int id;
    private String name;
    private Location location;

    public StopPoint(@JsonProperty("ID") int id,
                     @JsonProperty("Name") String name,
                     @JsonProperty("X") int x,
                     @JsonProperty("Y") int y) {
        this.id = id;
        this.name = name;
        this.location = new Location(x, y);
    }

}
