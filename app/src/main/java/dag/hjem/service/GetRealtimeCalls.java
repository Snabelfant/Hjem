package dag.hjem.service;

import android.os.AsyncTask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dag.hjem.model.travelproposal.RealtimeSearchResult;
import dag.hjem.ruter.model.MonitoredStopVisit;

/**
 * Created by Dag on 29.03.2015.
 */
class GetRealtimeCalls extends Service {
    Set<RealtimeCallId> candidates;

    GetRealtimeCalls(Collector collector) {
        super(collector, 10);

        candidates = new HashSet<>();
    }

    public void addCandidate(int ruterStopId, String lineNo) {
        RealtimeCallId callId = new RealtimeCallId(ruterStopId, lineNo);
        candidates.add(callId);
    }

    public void getCalls() {
        for (RealtimeCallId realtimeCallId : candidates) {
            getCall(realtimeCallId);
        }
    }

    void getCall(RealtimeCallId realtimeCallId) {
        AsyncTask<RealtimeCallId, Integer, RealtimeSearchResult> task = new AsyncTask<RealtimeCallId, Integer, RealtimeSearchResult>() {

            @Override
            protected RealtimeSearchResult doInBackground(RealtimeCallId... params) {
                int ruterStopId = params[0].getRuterStopId();
                String lineNo = params[0].getLineNo();
                try {
                    List<MonitoredStopVisit> monitoredStopVisits = ruterApi.getMonitorStopVisits(ruterStopId, lineNo);
                    return RealtimeSearchResult.fromRuter(ruterStopId, lineNo, monitoredStopVisits);
                } catch (Exception e) {
                    return RealtimeSearchResult.fromException(e);
                }
            }

            @Override
            protected void onPostExecute(RealtimeSearchResult result) {
                collector.setRealtimeSearchResult(result);
            }
        };

        task.executeOnExecutor(executorService, realtimeCallId);
    }


    private static class RealtimeCallId {
        private int ruterStopId;
        private String lineNo;

        private RealtimeCallId(int ruterStopId, String lineNo) {

            this.ruterStopId = ruterStopId;
            this.lineNo = lineNo;
        }

        @Override
        public boolean equals(Object o) {
            RealtimeCallId realtimeCallId = (RealtimeCallId) o;
            return ruterStopId == realtimeCallId.ruterStopId && lineNo.equals(realtimeCallId.lineNo);
        }

        @Override
        public int hashCode() {
            return ruterStopId + lineNo.hashCode();
        }

        public int getRuterStopId() {
            return ruterStopId;
        }

        public String getLineNo() {
            return lineNo;
        }
    }

}
