package dag.hjem.model.ruter;

import java.util.Date;
import java.util.List;

public class TravelProposal {
    private Date departureTime;
    private Date arrivalTime;
    private List<String> remarks;
    private String totalTravelTime;
    private List<Stage> stages;


    public TravelProposal( @JsonFormat(shape= JsonFormat.Shape.STRING,timezone="CET")
                           @JsonProperty("DepartureTime") Date departureTime,
                           @JsonFormat(shape= JsonFormat.Shape.STRING,timezone="CET")
                           @JsonProperty("ArrivalTime") Date arrivalTime,
                           @JsonProperty("Remarks") List<String> remarks,
                           @JsonProperty("TotalTravelTime") String totalTravelTime,
                           @JsonProperty("Stages") List<Stage> stages) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.remarks = remarks;
        this.totalTravelTime = totalTravelTime;
        this.stages = stages;
    }

    public String toString() {
        String s = departureTime.toString() + " - " + arrivalTime.toString() + " (" + totalTravelTime + ")";
        for (Stage stage : stages) {
            s += "\n" + stage.toString();
        }

        return s;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public List<Stage> getStages() {
        return stages;
    }
}
