package dag.hjem.model.location;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by Dag on 22.02.2015.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RuterLocation.class, name = "stopp"),
        @JsonSubTypes.Type(value = UtmLocation.class, name = "utm")})

public abstract class Location implements Serializable {
    public static final Location FROM_HERE = new Here("(Herfra)");
    public static final Location TO_HERE = new Here("(Hit)");
    public static final Location HJEM = new UtmLocation("Hjem", 583481, 6643824);
    private String name;

    protected Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public abstract String getDetails();

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return name.equals(((Location) o).name);
    }
}
