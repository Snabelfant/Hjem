package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.ruter.model.MonitoredCall;
import dag.hjem.ruter.model.MonitoredStopVisit;
import dag.hjem.ruter.model.MonitoredVehicleJourney;

/**
 * Created by Dag on 31.03.2015.
 */
public class RealtimeSearchResult {
    private int ruterStopId;
    private String lineNo;
    private List<RealtimeCall> realtimeCalls;
    private Exception exception;

    public static RealtimeSearchResult fromRuter(int ruterStopId, String lineNo, List<MonitoredStopVisit> monitoredStopVisits) {
        RealtimeSearchResult realtimeSearchResult = new RealtimeSearchResult();
        realtimeSearchResult.realtimeCalls = new ArrayList<>();
        realtimeSearchResult.ruterStopId = ruterStopId;
        realtimeSearchResult.lineNo = lineNo;

        for (MonitoredStopVisit monitoredStopVisit : monitoredStopVisits) {
            MonitoredVehicleJourney monitoredVehicleJourney = monitoredStopVisit.getMonitoredVehicleJourney();

            if (monitoredVehicleJourney.isMonitored()) {
                MonitoredCall monitoredCall = monitoredVehicleJourney.getMonitoredCall();
                RealtimeCall realtimeCall = new RealtimeCall(monitoredStopVisit.getRecordedAtTime(), monitoredVehicleJourney.isInCongestion(), monitoredCall.getAimedDepartureTime(), monitoredCall.getExpectedDepartureTime(), monitoredVehicleJourney.getDestinationName());

                realtimeSearchResult.realtimeCalls.add(realtimeCall);
            }
        }

        return realtimeSearchResult;
    }

    public static RealtimeSearchResult fromException(Exception exception) {
        RealtimeSearchResult searchResult = new RealtimeSearchResult();
        searchResult.exception = exception;
        return searchResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RealtimeSearchResult=")
                .append(realtimeCalls.size())
                .append("ex=")
                .append(exception)
                .append("\n");

        for (RealtimeCall realtimeCall : realtimeCalls) {
            sb.append(realtimeCall).append("\n");
        }
        return sb.toString();
    }

    public Exception getException() {
        return exception;
    }

}
