package dag.hjem.ruter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stop {
    private int id;
    private String name;
    private Location location;
    private String zone;
    private String shortName;
    private boolean isHub;
    private String district;
    private String placeType;

    public Stop(@JsonProperty("ID") int id,
                @JsonProperty("Name") String name,
                @JsonProperty("X") int x,
                @JsonProperty("Y") int y,
                @JsonProperty("Zone") String zone,
                @JsonProperty("ShortName") String shortName,
                @JsonProperty("IsHub") boolean isHub,
                @JsonProperty("District") String district,
                @JsonProperty("PlaceType") String placeType) {
        this.id = id;
        this.name = name;
        this.location = new Location(x, y);
        this.zone = zone;
        this.shortName = shortName;
        this.isHub = isHub;
        this.district = district;
        this.placeType = placeType;
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
                "l=" + location +
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