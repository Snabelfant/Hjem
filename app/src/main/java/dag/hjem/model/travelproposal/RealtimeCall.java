package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

/**
 * Created by Dag on 31.03.2015.
 */
public class RealtimeCall {
    private DateTime recordedAt;
    private boolean isInCongestion;
    private DateTime aimedDepartureTime;
    private DateTime expectedDepartureTime;
    private String destinationName;

    public RealtimeCall(DateTime recordedAt, boolean isInCongestion, DateTime aimedDepartureTime, DateTime expectedDepartureTime, String destinationName) {
        this.recordedAt = recordedAt;
        this.isInCongestion = isInCongestion;
        this.aimedDepartureTime = aimedDepartureTime;
        this.expectedDepartureTime = expectedDepartureTime;
        this.destinationName = destinationName;
    }

    public DateTime getAimedDepartureTime() {
        return aimedDepartureTime;
    }

    public DateTime getExpectedDepartureTime() {
        return expectedDepartureTime;
    }

    public String getDestinationName() {
        return destinationName;
    }

    @Override
    public String toString() {
        return "Rec=" + recordedAt + ", InC=" + isInCongestion + ", aimedDep=" + aimedDepartureTime + ", expDep=" + expectedDepartureTime + ", dest=" + destinationName;
    }
}
