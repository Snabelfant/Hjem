package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Stage {
    private final XY departurePoint;
    private final XY arrivalPoint;
    private final String walkingTime;
    private String destination;
    private ArrivalOrDepartureStop departureStop;
    private String departureTime;
    private int lineId;
    private String lineName;
    private TransportationType transportation;
    private String lineColour;
    private String travelTime;
    private ArrivalOrDepartureStop arrivalStop;
    private String arrivalTime;
    private List<Deviation> deviations;
    private String operator;
    private List<String> remarks;
    private int tourId;
    private int tourLineId;


    private Stage(@JsonProperty("ArrivalStop") ArrivalOrDepartureStop arrivalStop,
                  @JsonProperty("ArrivalTime") String arrivalTime,
                  @JsonProperty("Deviations") List<Deviation> deviations,
                  @JsonProperty("DepartureStop") ArrivalOrDepartureStop departureStop,
                  @JsonProperty("DepartureTime") String departureTime,
                  @JsonProperty("Destination") String destination,
                  @JsonProperty("LineID") int lineId,
                  @JsonProperty("LineName") String lineName,
                  @JsonProperty("Transportation") TransportationType transportation,
                  @JsonProperty("LineColour") String lineColour,
                  @JsonProperty("Operator") String operator,
                  @JsonProperty("Remarks") List<String> remarks,
                  @JsonProperty("TravelTime") String travelTime,
                  @JsonProperty("TourID") int tourId,
                  @JsonProperty("TourLineID") int tourLineId,
                  @JsonProperty("ArrivalPoint") XY arrivalPoint,
                  @JsonProperty("DeparturePoint") XY departurePoint,
                  @JsonProperty("WalkingTime") String walkingTime
    ) {

        this.arrivalStop = arrivalStop;
        this.arrivalTime = arrivalTime;
        this.deviations = deviations;
        this.departureStop = departureStop;
        this.departureTime = departureTime;
        this.destination = destination;
        this.lineId = lineId;
        this.lineName = lineName;
        this.transportation = transportation;
        this.lineColour = lineColour;
        this.operator = operator;
        this.remarks = remarks;
        this.travelTime = travelTime;
        this.tourId = tourId;
        this.tourLineId = tourLineId;
        this.arrivalPoint = arrivalPoint;
        this.departurePoint = departurePoint;
        this.walkingTime = walkingTime;
    }

    public String getDepartureStopName() {
        return departureStop == null ? null : departureStop.getName();
    }

    public String getArrivalStopName() {
        return arrivalStop == null ? null : arrivalStop.getName();
    }


    public String toString() {
        if (TransportationType.WALKING.equals(transportation)) {
            return transportation + " (" + walkingTime +") "+  arrivalTime;

        } else {

            return departureTime + " - " +
                    getDepartureStopName() + " " +
                    lineName + "/" + transportation + ": (" + destination + ") " +
                    arrivalTime + " - " + getArrivalStopName();
        }
    }

    public String getWalkingTime() {
        return walkingTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public String getLineName() {
        return lineName;
    }

    public TransportationType getTransportation() {
        return transportation;
    }

    public String getDestination() {
        return destination;
    }

    public List<Deviation> getDeviations() {
        return deviations;
    }
}
