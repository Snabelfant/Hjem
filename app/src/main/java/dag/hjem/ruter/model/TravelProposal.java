package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.util.List;

import dag.hjem.Util;

public class TravelProposal {
    private DateTime departureTime;
    private DateTime arrivalTime;
    private List<String> remarks;
    private Duration totalTravelTime;
    private List<Stage> stages;
    private String zones;

    public TravelProposal(
            @JsonProperty("DepartureTime") String departureTime,
            @JsonProperty("ArrivalTime") String arrivalTime,
            @JsonProperty("Remarks") List<String> remarks,
            @JsonProperty("TotalTravelTime") String totalTravelTime,
            @JsonProperty("Stages") List<Stage> stages,
            @JsonProperty("Zones") String zones) {
        this.departureTime = new DateTime(departureTime, DateTimeZone.getDefault());
        this.arrivalTime = new DateTime(arrivalTime, DateTimeZone.getDefault());
        this.remarks = remarks;
        this.totalTravelTime = Util.toDuration(totalTravelTime);
        this.stages = stages;
        this.zones = zones;
    }

    public Duration getTotalTravelTime() {
        return totalTravelTime;
    }

    public String toString() {
        String s = departureTime.toString() + " - " + arrivalTime.toString() + " (" + totalTravelTime.getStandardMinutes() + ")";
        for (Stage stage : stages) {
            s += "\n" + stage.toString();
        }

        return s + "\n";
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public List<Stage> getStages() {
        return stages;
    }
}
