package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(value = {"BlockRef", "Delay", "Extensions", "DirectionRef", "DirectionName", "DestinationAimedArrivalTime", "FramedVehicleJourneyRef",
        "OperatorRef", "OriginAimedDepartureTime", "OriginName", "OriginRef", "PublishedLineName", "TrainBlockPart", "VehicleFeatureRef",
        "VehicleJourneyName", "VehicleMode", "VehicleRef"})
public class MonitoredVehicleJourney {
    private String lineRef;
    private String destinationName;
    private String destinationRef;
    private boolean isInCongestion;
    private boolean isMonitored;
    private MonitoredCall monitoredCall;

    public MonitoredVehicleJourney(@JsonProperty("LineRef") String lineRef,
                                   @JsonProperty("DestinationName") String destinationName,
                                   @JsonProperty("DestinationRef") String destinationRef,
                                   @JsonProperty("InCongestion") boolean isInCongestion,
                                   @JsonProperty("Monitored") boolean isMonitored,
                                   @JsonProperty("MonitoredCall") MonitoredCall monitoredCall) {
        this.lineRef = lineRef;
        this.destinationName = destinationName;
        this.destinationRef = destinationRef;
        this.isInCongestion = isInCongestion;
        this.isMonitored = isMonitored;
        this.monitoredCall = monitoredCall;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public boolean isInCongestion() {
        return isInCongestion;
    }

    public boolean isMonitored() {
        return isMonitored;
    }

    public MonitoredCall getMonitoredCall() {
        return monitoredCall;
    }
}
