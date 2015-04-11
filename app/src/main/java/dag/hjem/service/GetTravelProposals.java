package dag.hjem.service;

import android.os.AsyncTask;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.RuterLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.Section;
import dag.hjem.model.travelproposal.Travel;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.model.travelproposal.TravelSection;
import dag.hjem.ruter.model.TravelResponse;

class GetTravelProposals extends Service {
    GetTravelProposals(Collector collector) {
        super(collector, 1);
    }

    void getTravelProposals(Location fromLocation, Location toLocation, boolean isAfter, DateTime departureOrArrivalTime) throws IOException {
        GetTravelProposalsParams params = new GetTravelProposalsParams(fromLocation, toLocation, isAfter, departureOrArrivalTime);
        AsyncTask<GetTravelProposalsParams, Integer, TravelSearchResult> task = new AsyncTask<GetTravelProposalsParams, Integer, TravelSearchResult>() {

            @Override
            protected TravelSearchResult doInBackground(GetTravelProposalsParams... params) {
                GetTravelProposalsParams param = params[0];
                try {
                    TravelResponse travelResponse = ruterApi.getTravels(param.getFromId(), param.getFromX(), param.getFromY(),
                            param.getToId(), param.getToX(), param.getToY(), param.isAfter(), param.getDepartureOrArrivalTime());
                    return TravelSearchResult.fromRuter(param.getFromName(), param.getToName(), travelResponse);
                } catch (IOException e) {
                    return TravelSearchResult.fromException(e);
                }
            }

            @Override
            protected void onPostExecute(TravelSearchResult result) {
                collector.setTravelSearchResult(result);
                if (result.getException() == null) {
                    getRealtimeCalls(result.getTravels(), collector);
                }
            }
        };

        task.executeOnExecutor(executorService, params);
    }

    private void getRealtimeCalls(List<Travel> travels, Collector collector) {
        GetRealtimeCalls getRealtimeCalls = new GetRealtimeCalls(collector);
        for (Travel travel : travels) {
            for (Section section : travel.getSections()) {
                if (section instanceof TravelSection) {
                    TravelSection travelSection = (TravelSection) section;
                    String line = travelSection.getLine().getLineName();
                    int ruterStopId = travelSection.getDepartureStopId();
                    getRealtimeCalls.addCandidate(ruterStopId, line);
                }
            }
        }

        getRealtimeCalls.getCalls();
    }

    private static class GetTravelProposalsParams {
        String fromName;
        String toName;
        Integer fromId = null;
        Integer toId = null;
        Integer fromX = null;
        Integer fromY = null;
        Integer toX = null;
        Integer toY = null;
        boolean isAfter;
        DateTime departureOrArrivalTime;

        protected GetTravelProposalsParams(Location fromLocation, Location toLocation, boolean isAfter, DateTime departureOrArrivalTime) {
            fromName = fromLocation.getName();
            toName = toLocation.getName();
            if (fromLocation instanceof RuterLocation) {
                fromId = ((RuterLocation) fromLocation).getRuterId();
            } else {
                fromX = ((UtmLocation) fromLocation).getX();
                fromY = ((UtmLocation) fromLocation).getY();
            }

            if (toLocation instanceof RuterLocation) {
                toId = ((RuterLocation) toLocation).getRuterId();
            } else {
                toX = ((UtmLocation) toLocation).getX();
                toY = ((UtmLocation) toLocation).getY();
            }

            this.isAfter = isAfter;
            this.departureOrArrivalTime = departureOrArrivalTime;
        }

        public String getToName() {

            return toName;
        }

        public String getFromName() {
            return fromName;
        }

        public Integer getFromId() {
            return fromId;
        }

        public Integer getToId() {
            return toId;
        }

        public Integer getFromX() {
            return fromX;
        }

        public Integer getFromY() {
            return fromY;
        }

        public Integer getToX() {
            return toX;
        }

        public Integer getToY() {
            return toY;
        }

        public boolean isAfter() {
            return isAfter;
        }

        public DateTime getDepartureOrArrivalTime() {
            return departureOrArrivalTime;
        }

    }


}
