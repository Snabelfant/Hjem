package dag.hjem.model.ruter;

import java.util.Date;
import java.util.List;

import static dag.hjem.model.ruter.Util.formatDuration;
import static dag.hjem.model.ruter.Util.formatTime;


public class TravelStage extends StageX {
    private Date departureTime;
    private String departureStopName;
    private Date arrivalTime;
    private String arrivalStopName;
    private Duration travelTime;
    private String transportation;
    private String lineName;
    private String destination;
    private List<String> deviations;

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
