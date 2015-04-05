package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(value = {"MonitoringRef", "Extensions"})
public class MonitoredStopVisit {
    private String recordedAtTime;
    private MonitoredVehicleJourney monitoredVehicleJourney;

    public MonitoredStopVisit(@JsonProperty("RecordedAtTime") String recordedAtTime, @JsonProperty("MonitoredVehicleJourney") MonitoredVehicleJourney monitoredVehicleJourney) {
        this.recordedAtTime = recordedAtTime;
        this.monitoredVehicleJourney = monitoredVehicleJourney;
    }

    public String getRecordedAtTime() {
        return recordedAtTime;
    }

    public MonitoredVehicleJourney getMonitoredVehicleJourney() {
        return monitoredVehicleJourney;
    }
}
