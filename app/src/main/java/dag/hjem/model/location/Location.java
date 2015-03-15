package dag.hjem.model.location;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by Dag on 22.02.2015.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "Type")
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

    @Override
    public String toString() {
        return name;
    }
}
