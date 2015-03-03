package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dag.hjem.model.travelproposal.Duration;
import dag.hjem.model.travelproposal.Section;

import static dag.hjem.model.travelproposal.Util.formatDuration;
import static dag.hjem.model.travelproposal.Util.formatTime;


public class TravelSection extends Section {
    private Date departureTime;
    private String departureStopName;
    private Date arrivalTime;
    private String arrivalStopName;
    private Duration travelTime;
    private String transportation;
    private String lineName;
    private String destination;

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureStopName() {
        return departureStopName;
    }

    public void setDepartureStopName(String departureStopName) {
        this.departureStopName = departureStopName;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalStopName() {
        return arrivalStopName;
    }

    public void setArrivalStopName(String arrivalStopName) {
        this.arrivalStopName = arrivalStopName;
    }

    public Duration getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Duration travelTime) {
        this.travelTime = travelTime;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    private List<String> deviations = new ArrayList<>();

    public void addDeviation(String deviation) {
        deviations.add(deviation);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("  - ")
                .append(transportation).append(" ")
                .append(lineName).append("/").append(destination).append(": ")
                .append(formatTime(departureTime)).append("/").append(departureStopName)
                .append(" - ")
                .append(formatTime(arrivalTime)).append("/").append(arrivalStopName)
                .append(" (").append(formatDuration(travelTime)).append(")\n");

        for (String deviation : deviations) {
            s.append("     ! ").append(deviation).append("\n");
        }

        return s.toString();
    }
}
