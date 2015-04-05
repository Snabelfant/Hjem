package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;


public class TravelSection extends MovementSection {
    private String departureStopName;
    private int departureStopId;
    private String arrivalStopName;
    private String travelTime;
    private Line line;
    private String destination;
    private List<String> deviations = new ArrayList<>();

    public TravelSection(String departureTime, String arrivalTime) {
        super(departureTime, arrivalTime);
    }

    public int getDepartureStopId() {
        return departureStopId;
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

    public String getDepartureStopName() {
        return departureStopName;
    }

    public void setDepartureStop(String departureStopName, int departureStopId) {
        this.departureStopName = departureStopName;
        this.departureStopId = departureStopId;
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

    public boolean hasDeviations() {
        return deviations != null && deviations.size() > 0;
    }

    public String getDeviations() {
        StringBuilder sb = new StringBuilder();

        for (String line : deviations) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }
}
