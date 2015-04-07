package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.util.List;

import dag.hjem.Util;

public class Stage {
    private final XY departurePoint;
    private final XY arrivalPoint;
    private final Duration walkingTime;
    private String destination;
    private ArrivalOrDepartureStop departureStop;
    private DateTime departureTime;
    private int lineId;
    private String lineName;
    private TransportationType transportation;
    private String lineColour;
    private Duration travelTime;
    private ArrivalOrDepartureStop arrivalStop;
    private DateTime arrivalTime;
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
        this.arrivalTime = new DateTime(arrivalTime, DateTimeZone.getDefault());
        this.deviations = deviations;
        this.departureStop = departureStop;
        this.departureTime = new DateTime(departureTime, DateTimeZone.getDefault());
        this.destination = destination;
        this.lineId = lineId;
        this.lineName = lineName;
        this.transportation = transportation;
        this.lineColour = lineColour;
        this.operator = operator;
        this.remarks = remarks;
        this.travelTime = Util.toDuration(travelTime);
        this.tourId = tourId;
        this.tourLineId = tourLineId;
        this.arrivalPoint = arrivalPoint;
        this.departurePoint = departurePoint;
        this.walkingTime = Util.toDuration(walkingTime);
    }

    public String getDepartureStopName() {
        return departureStop == null ? null : departureStop.getName();
    }

    public int getDepartureStopId() {
        return departureStop == null ? null : departureStop.getId();
    }

    public String getArrivalStopName() {
        return arrivalStop == null ? null : arrivalStop.getName();
    }


    public String toString() {
        if (TransportationType.WALKING.equals(transportation)) {
            return transportation + " (" + walkingTime + ") " + arrivalTime;

        } else {

            return departureTime + " - " +
                    getDepartureStopName() + " " +
                    lineName + "/" + transportation + ": (" + destination + ") " +
                    arrivalTime + " - " + getArrivalStopName();
        }
    }

    public Duration getWalkingTime() {
        return walkingTime;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public Duration getTravelTime() {
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
