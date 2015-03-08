package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by Dag on 07.03.2015.
 */
public class Place {
    private int id;
    private int x;
    private int y;
    private String zone;
    private String shortName;
    private boolean isHub;
    private PlaceType placeType;
    private String name;
    private String district;
    private boolean isAlightingAllowed;
    private boolean isBoardingAllowed;
    private boolean isRealTimeStop;
    private List<Line> lines;
    private List<StopPoint> stopPoints;
    private List<Deviation> deviations;
    private List<Stop> stops;
    private XY center;
    private List<House> houses;


    public Place(@JsonProperty("ID") int id,
                 @JsonProperty("X") int x,
                 @JsonProperty("Y") int y,
                 @JsonProperty("Zone") String zone,
                 @JsonProperty("ShortName") String shortName,
                 @JsonProperty("IsHub") boolean isHub,
                 @JsonProperty("PlaceType") PlaceType placeType,
                 @JsonProperty("Name") String name,
                 @JsonProperty("District") String district,
                 @JsonProperty("AlightingAllowed") boolean isAlightingAllowed,
                 @JsonProperty("BoardingAllowed") boolean isBoardingAllowed,
                 @JsonProperty("RealTimeStop") boolean isRealTimeStop,
                 @JsonProperty("Lines") List<Line> lines,
                 @JsonProperty("StopPoints") List<StopPoint> stopPoints,
                 @JsonProperty("Deviations") List<Deviation> deviations,
                 @JsonProperty("Stops") List<Stop> stops,
                 @JsonProperty("Center") XY center,
                 @JsonProperty("Houses") List<House> houses

    ) {

        this.id = id;
        this.x = x;
        this.y = y;
        this.zone = zone;
        this.shortName = shortName;
        this.isHub = isHub;
        this.placeType = placeType;
        this.name = name;
        this.district = district;
        this.isAlightingAllowed = isAlightingAllowed;
        this.isBoardingAllowed = isBoardingAllowed;
        this.isRealTimeStop = isRealTimeStop;
        this.lines = lines;
        this.stopPoints = stopPoints;
        this.deviations = deviations;
        this.stops = stops;
        this.center = center;
        this.houses = houses;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", placeType=" + placeType +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
