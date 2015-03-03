package dag.hjem.ruter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ArrivalOrDepartureStop {
    private int id;
    private boolean alightingAllowed;
    private boolean boardingAllowed;
    private boolean realTimeStop;
    private List<Line> lines;
    private List<StopPoint> stopPoints;
    private List<Deviation> deviations;
    private String name;
    private Location location;
    private String zone;
    private String shortName;
    private boolean isHub;
    private String district;
    private String placeType;


    public ArrivalOrDepartureStop(@JsonProperty("ID") int id,
                                  @JsonProperty("AlightingAllowed") boolean alightingAllowed,
                                  @JsonProperty("BoardingAllowed") boolean boardingAllowed,
                                  @JsonProperty("RealTimeStop") boolean realTimeStop,
                                  @JsonProperty("Lines") List<Line> lines,
                                  @JsonProperty("StopPoints") List<StopPoint> stopPoints,
                                  @JsonProperty("Deviations") List<Deviation> deviations,
                                  @JsonProperty("X") int x,
                                  @JsonProperty("Y") int y,
                                  @JsonProperty("Zone") String zone,
                                  @JsonProperty("ShortName") String shortName,
                                  @JsonProperty("IsHub") boolean isHub,
                                  @JsonProperty("Name") String name,
                                  @JsonProperty("District") String district,
                                  @JsonProperty("PlaceType") String placeType
    ) {
        this.id = id;
        this.alightingAllowed = alightingAllowed;
        this.boardingAllowed = boardingAllowed;
        this.realTimeStop = realTimeStop;
        this.lines = lines;
        this.stopPoints = stopPoints;
        this.deviations = deviations;
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
}
