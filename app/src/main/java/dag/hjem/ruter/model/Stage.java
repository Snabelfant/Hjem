package dag.hjem.ruter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Stage {
    private final Location departurePoint;
    private final Location arrivalPoint;
    private final String walkingTime;
    private String destination;
    private ArrivalOrDepartureStop departureStop;
    private Date departureTime;
    private int lineId;
    private String lineName;
    private TransportationType transportation;
    private String lineColour;
    private String travelTime;
    private ArrivalOrDepartureStop arrivalStop;
    private Date arrivalTime;
    private List<Deviation> deviations;
    private String operator;
    private List<String> remarks;
    private int tourId;
    private int tourLineId;


    private Stage(@JsonProperty("ArrivalStop") ArrivalOrDepartureStop arrivalStop,
                  @JsonFormat(shape= JsonFormat.Shape.STRING,timezone="CET")
                  @JsonProperty("ArrivalTime") Date arrivalTime,
                  @JsonProperty("Deviations") List<Deviation> deviations,
                  @JsonProperty("DepartureStop") ArrivalOrDepartureStop departureStop,
                  @JsonFormat(shape= JsonFormat.Shape.STRING,timezone="CET")
                  @JsonProperty("DepartureTime") Date departureTime,
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
                  @JsonProperty("ArrivalPoint") Location arrivalPoint,
                  @JsonProperty("DeparturePoint") Location departurePoint,
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
        return departureStop.getName();
    }

    public String getArrivalStopName() {
        return arrivalStop.getName();
    }


    public String toString() {
        return " " + departureTime + " - " +
                getDepartureStopName() + " " +
                lineName + ": (" + destination + ") " +
                arrivalTime;
    }

    public String getWalkingTime() {
        return walkingTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
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
