package dag.hjem.ruter.model;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@JsonIgnoreProperties(value = {"DeparturePlatformName", "DestinationDisplay", "VehicleAtStop", "VisitNumber"})

public class MonitoredCall {

    private DateTime aimedArrivalTime;
    private DateTime aimedDepartureTime;
    private DateTime expectedArrivalTime;
    private DateTime expectedDepartureTime;

    public MonitoredCall(@JsonProperty("AimedArrivalTime") String aimedArrivalTime,
                         @JsonProperty("AimedDepartureTime") String aimedDepartureTime,
                         @JsonProperty("ExpectedArrivalTime") String expectedArrivalTime,
                         @JsonProperty("ExpectedDepartureTime") String expectedDepartureTime) {
        this.aimedArrivalTime = new DateTime(aimedArrivalTime, DateTimeZone.getDefault());
        this.aimedDepartureTime = new DateTime(aimedDepartureTime, DateTimeZone.getDefault());
        this.expectedArrivalTime = new DateTime(expectedArrivalTime, DateTimeZone.getDefault());
        this.expectedDepartureTime = new DateTime(expectedDepartureTime, DateTimeZone.getDefault());
    }

    public DateTime getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    public DateTime getExpectedDepartureTime() {
        return expectedDepartureTime;
    }
}
