package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    private DepartureOrArrivalTime departureTime;
    private DepartureOrArrivalTime arrivalTime;
    private Duration totalTravelTime;
    private List<String> remarks;
    private List<Line> lines;

    public Summary(DateTime departureTime, DateTime arrivalTime, Duration totalTravelTime, List<Section> sections, List<String> remarks) {
        this.departureTime = new DepartureOrArrivalTime(departureTime);
        this.arrivalTime = new DepartureOrArrivalTime(arrivalTime);
        this.totalTravelTime = totalTravelTime;
        this.remarks = remarks;

        lines = new ArrayList<>();

        for (Section section : sections) {
            if (section instanceof TravelSection) {
                lines.add(((TravelSection) section).getLine());
            }
        }
    }

    public DepartureOrArrivalTime getDepartureTime() {
        return departureTime;
    }

    public DepartureOrArrivalTime getArrivalTime() {
        return arrivalTime;
    }

    public Duration getTotalTravelTime() {
        return totalTravelTime;
    }

    public boolean hasRemarks() {
        return remarks != null && remarks.size() > 0;
    }

    public String getRemarks() {
        StringBuilder sb = new StringBuilder();

        for (String line : remarks) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    public String toString() {
        return new StringBuilder().append("++ ")
                .append(departureTime)
                .append(" - ")
                .append(arrivalTime)
                .append(" (")
                .append(totalTravelTime)
                .append(") ").append(lines.toString()).toString();
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setRealtimeDepartureTime(DateTime expectedDepartureTime) {
        departureTime = new DepartureOrArrivalTime(expectedDepartureTime, true);
    }
}
