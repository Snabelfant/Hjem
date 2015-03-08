package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Line {
    private int id;
    private String name;
    private TransportationType transportation;
    private String lineColour;

    public Line(@JsonProperty("ID") int id,
                @JsonProperty("Name") String name,
                @JsonProperty("Transportation") TransportationType transportation,
                @JsonProperty("LineColour") String lineColour) {
        this.id = id;
        this.name = name;
        this.transportation = transportation;
        this.lineColour = lineColour;
    }

    public TransportationType getTransportation() {
        return transportation;
    }

    public String getName() {
        return name;
    }


}
