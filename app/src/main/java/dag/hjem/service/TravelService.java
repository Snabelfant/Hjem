package dag.hjem.service;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.StopLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.Place;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.ruter.model.TravelResponse;

public class TravelService {
    private RuterApi ruterApi;
    private TravelServiceCollector travelServiceCollector;
    private ExecutorService executorService;

    public TravelService(RuterApi ruterApi, TravelServiceCollector travelServiceCollector) {
        this.ruterApi = ruterApi;
        this.travelServiceCollector = travelServiceCollector;
        this.executorService = Executors.newSingleThreadExecutor();

    }

    public boolean ping() throws IOException {
        return ruterApi.heartBeat();
    }

    public void getTravelProposals(Location fromLocation, Location toLocation, boolean isAfter, Calendar departureOrArrivalTime) throws IOException {
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
                travelServiceCollector.setTravelSearchResult(result);
            }
        };

        task.executeOnExecutor(executorService, params);

    }

    public void getPlaces(String partialName) throws IOException {
        AsyncTask<String, Integer, PlaceSearchResult> task = new AsyncTask<String, Integer, PlaceSearchResult>() {

            @Override
            protected PlaceSearchResult doInBackground(String... params) {
                String partialName = params[0];
                try {
                    List<dag.hjem.ruter.model.Place> ruterPlaceSearchResult  = ruterApi.getPlaces(partialName);
                    return PlaceSearchResult.fromRuter(ruterPlaceSearchResult);
                } catch (IOException e) {
                    return PlaceSearchResult.fromException(e);
                }
            }

            @Override
            protected void onPostExecute(PlaceSearchResult result) {
                travelServiceCollector.setPlaceSearchResult(result);
            }
        };

        task.executeOnExecutor(executorService, partialName);

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
        Calendar departureOrArrivalTime;

        protected GetTravelProposalsParams(Location fromLocation, Location toLocation, boolean isAfter, Calendar departureOrArrivalTime) {
            fromName = fromLocation.getName();
            toName = toLocation.getName();
            if (fromLocation instanceof StopLocation) {
                fromId = ((StopLocation) fromLocation).getRuterId();
            } else {
                fromX = ((UtmLocation) fromLocation).getX();
                fromY = ((UtmLocation) fromLocation).getY();
            }

            if (toLocation instanceof StopLocation) {
                toId = ((StopLocation) toLocation).getRuterId();
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

        public Calendar getDepartureOrArrivalTime() {
            return departureOrArrivalTime;
        }

    }
}
