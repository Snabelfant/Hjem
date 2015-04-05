package dag.hjem.model.travelproposal;

/**
 * Created by Dag on 31.03.2015.
 */
public class RealtimeCall {
    private String recordedAt;
    private boolean isInCongestion;
    private String aimedDepartureTime;
    private String expectedDepartureTime;
    private String destinationName;

    public RealtimeCall(String recordedAt, boolean isInCongestion, String aimedDepartureTime, String expectedDepartureTime, String destinationName) {
        this.recordedAt = recordedAt;
        this.isInCongestion = isInCongestion;
        this.aimedDepartureTime = aimedDepartureTime;
        this.expectedDepartureTime = expectedDepartureTime;
        this.destinationName = destinationName;
    }

    @Override
    public String toString() {
        return "Rec=" + recordedAt + ", InC=" + isInCongestion + ", aimedDep=" + aimedDepartureTime + ", expDep=" + expectedDepartureTime + ", dest=" + destinationName;
    }
}
