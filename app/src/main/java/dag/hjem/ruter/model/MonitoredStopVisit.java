package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@JsonIgnoreProperties(value = {"MonitoringRef", "Extensions"})
public class MonitoredStopVisit {
    private DateTime recordedAtTime;
    private MonitoredVehicleJourney monitoredVehicleJourney;

    public MonitoredStopVisit(@JsonProperty("RecordedAtTime") String recordedAtTime, @JsonProperty("MonitoredVehicleJourney") MonitoredVehicleJourney monitoredVehicleJourney) {
        this.recordedAtTime = new DateTime(recordedAtTime, DateTimeZone.getDefault());
        this.monitoredVehicleJourney = monitoredVehicleJourney;
    }

    public DateTime getRecordedAtTime() {
        return recordedAtTime;
    }

    public MonitoredVehicleJourney getMonitoredVehicleJourney() {
        return monitoredVehicleJourney;
    }
}
