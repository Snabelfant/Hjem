package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dag.hjem.model.travelproposal.Section;

import static dag.hjem.model.travelproposal.Util.formatTime;


public class TravelSection extends Section {
    private String departureTime;
    private String departureStopName;
    private String arrivalTime;
    private String arrivalStopName;
    private String travelTime;
    private String transportation;
    private String lineName;
    private String destination;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureStopName() {
        return departureStopName;
    }

    public void setDepartureStopName(String departureStopName) {
        this.departureStopName = departureStopName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalStopName() {
        return arrivalStopName;
    }

    public void setArrivalStopName(String arrivalStopName) {
        this.arrivalStopName = arrivalStopName;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
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
                .append(departureTime).append("/").append(departureStopName).append(": ")
                .append(transportation).append(" ").append(lineName).append(" (").append(destination).append(") -> ")
                .append(arrivalTime).append("/").append(arrivalStopName)
                .append(" (").append(travelTime).append(")\n");

        for (String deviation : deviations) {
            s.append("     ! ").append(deviation).append("\n");
        }

        return s.toString();
    }
}
