package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReisError {
    private String id;
    private String description;

    public ReisError(@JsonProperty("ID") String id, @JsonProperty("Description") String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReisError{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
