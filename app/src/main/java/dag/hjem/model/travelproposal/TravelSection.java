package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;


public class TravelSection extends MovementSection {
    private String departureStopName;
    private String arrivalStopName;
    private String travelTime;
    private Line line;
    private String destination;
    private List<String> deviations = new ArrayList<>();

    public TravelSection(String departureTime, String arrivalTime) {
        super(departureTime, arrivalTime);
    }

    public String getDepartureStopName() {
        return departureStopName;
    }

    public void setDepartureStopName(String departureStopName) {
        this.departureStopName = departureStopName;
    }

    public void setArrivalStopName(String arrivalStopName) {
        this.arrivalStopName = arrivalStopName;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(String lineName, TransportationType type) {
        Line line = new Line(lineName, type);
        this.line = line;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void addDeviation(String deviation) {
        deviations.add(deviation);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("  - ")
                .append(getDepartureTime()).append("/").append(departureStopName).append(": ")
                .append(line.toString()).append(" (").append(destination).append(") -> ")
                .append(getArrivalTime()).append("/").append(arrivalStopName)
                .append(" (").append(travelTime).append(")");

        for (String deviation : deviations) {
            s.append("\n").append("     ! ").append(deviation);
        }

        return s.toString();
    }
}
