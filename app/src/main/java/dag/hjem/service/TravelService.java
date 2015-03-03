package dag.hjem.service;

import java.util.Date;

import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.ruter.model.Place;
import dag.hjem.ruter.model.TravelResponse;

/**
 * Created by Dag on 03.03.2015.
 */
public class TravelService {

    private RuterApi ruterApi;

    public TravelService(RuterApi ruterApi) {
        this.ruterApi = ruterApi;
    }

    public boolean ping() {
        return ruterApi.heartBeat();
    }
    private TravelSearchResult getTravelProposals(Place fromPlace, Place toPlace, boolean isAfter, Date departureOrArrivalTime) {
        TravelResponse travelResponse = ruterApi.getTravel(fromPlace, toPlace, isAfter, departureOrArrivalTime);
        return TravelSearchResult.fromRuter(fromPlace.getName(), toPlace.getName(), travelResponse);
    }


}
