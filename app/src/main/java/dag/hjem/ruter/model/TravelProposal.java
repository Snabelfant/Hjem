package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

public class TravelProposal {
    private String departureTime;
    private String arrivalTime;
    private List<String> remarks;
    private String totalTravelTime;
    private List<Stage> stages;
    private String zones;

    public TravelProposal(
                          @JsonProperty("DepartureTime") String departureTime,
                          @JsonProperty("ArrivalTime") String arrivalTime,
                          @JsonProperty("Remarks") List<String> remarks,
                          @JsonProperty("TotalTravelTime") String totalTravelTime,
                          @JsonProperty("Stages") List<Stage> stages,
                          @JsonProperty("Zones") String zones) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.remarks = remarks;
        this.totalTravelTime = totalTravelTime;
        this.stages = stages;
        this.zones = zones;
    }

    public String getTotalTravelTime() {
        return totalTravelTime;
    }

    public String toString() {
        String s = departureTime.toString() + " - " + arrivalTime.toString() + " (" + totalTravelTime + ")";
        for (Stage stage : stages) {
            s += "\n" + stage.toString();
        }

        return s + "\n";
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public List<Stage> getStages() {
        return stages;
    }
}
