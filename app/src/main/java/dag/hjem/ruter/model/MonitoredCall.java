package dag.hjem.ruter.model;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(value = {"DeparturePlatformName", "DestinationDisplay", "VehicleAtStop", "VisitNumber"})

public class MonitoredCall {

    private String aimedArrivalTime;
    private String aimedDepartureTime;
    private String expectedArrivalTime;
    private String expectedDepartureTime;

    public MonitoredCall(@JsonProperty("AimedArrivalTime") String aimedArrivalTime,
                         @JsonProperty("AimedDepartureTime") String aimedDepartureTime,
                         @JsonProperty("ExpectedArrivalTime") String expectedArrivalTime,
                         @JsonProperty("ExpectedDepartureTime") String expectedDepartureTime) {
        this.aimedArrivalTime = aimedArrivalTime;
        this.aimedDepartureTime = aimedDepartureTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.expectedDepartureTime = expectedDepartureTime;
    }

    public String getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    public String getExpectedDepartureTime() {
        return expectedDepartureTime;
    }
}
