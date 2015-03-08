package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Stop {
    private int id;
    private String name;
    private int x;
    private int y;
    private String zone;
    private String shortName;
    private boolean isHub;
    private String district;
    private PlaceType placeType;
    private int walkingMinutes;


    @JsonCreator
    public Stop(@JsonProperty("ID") int id,
                @JsonProperty("Name") String name,
                @JsonProperty("X") int x,
                @JsonProperty("Y") int y,
                @JsonProperty("Zone") String zone,
                @JsonProperty("ShortName") String shortName,
                @JsonProperty("IsHub") boolean isHub,
                @JsonProperty("District") String district,
                @JsonProperty("PlaceType") PlaceType placeType,
                @JsonProperty("WalkingMinutes") int walkingMinutes) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.zone = zone;
        this.shortName = shortName;
        this.isHub = isHub;
        this.district = district;
        this.placeType = placeType;
        this.walkingMinutes = walkingMinutes;
    }

    public String getName() {

        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "x=" + x +
                ", y=" + y +
                ", zone='" + zone + '\'' +
                ", shortName='" + shortName + '\'' +
                ", isHub=" + isHub +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", placeType='" + placeType + '\'' +
                '}';
    }

}