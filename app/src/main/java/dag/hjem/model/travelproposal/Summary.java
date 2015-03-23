package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    private String departureTime;
    private String arrivalTime;
    private String totalTravelTime;
    private List<String> remarks;
    private List<Line> lines;

    public Summary(String departureTime, String arrivalTime, String totalTravelTime, List<Section> sections, List<String> remarks) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
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
