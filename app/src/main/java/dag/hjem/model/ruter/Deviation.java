package dag.hjem.model.ruter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deviation {
    private int id;
    private String header;

    public Deviation(@JsonProperty("ID") int id,
                     @JsonProperty("Header") String header) {
        this.id = id;
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
