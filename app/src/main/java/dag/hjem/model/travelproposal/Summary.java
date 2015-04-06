package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    private DateTime departureTime;
    private DateTime arrivalTime;
    private String totalTravelTime;
    private List<String> remarks;
    private List<Line> lines;

    public Summary(DateTime departureTime, DateTime arrivalTime, String totalTravelTime, List<Section> sections, List<String> remarks) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalTravelTime = totalTravelTime;
        this.remarks = remarks;

        lines = new ArrayList<>();

        for (Section section : sections) {
            if (section instanceof TravelSection) {
                lines.add(((TravelSection) section).getLine());
            }
        }
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public String getDepartureTimeFormatted() {
        return Util.formatTime(departureTime);
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getArrivalTimeFormatted() {
        return Util.formatTime(arrivalTime);
    }

    public String getTotalTravelTime() {
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
}
