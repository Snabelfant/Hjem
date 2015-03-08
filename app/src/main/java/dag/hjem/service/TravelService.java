package dag.hjem.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatCodePointException;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.StopLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.ruter.model.TravelResponse;

/**
 * Created by Dag on 03.03.2015.
 */
public class TravelService {

    private RuterApi ruterApi;

    public TravelService(RuterApi ruterApi) {
        this.ruterApi = ruterApi;
    }

    public boolean ping() throws IOException {
        return ruterApi.heartBeat();
    }

    public TravelSearchResult getTravelProposals(Location fromLocation, Location toLocation, boolean isAfter, Calendar departureOrArrivalTime) throws IOException {
        Integer fromId = null;
        Integer toId = null;
        Integer fromX = null;
        Integer fromY = null;
        Integer toX = null;
        Integer toY = null;

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

        TravelResponse travelResponse = ruterApi.getTravels(fromId,fromX, fromY, toId, toX, toY, isAfter, departureOrArrivalTime);
        return TravelSearchResult.fromRuter(fromLocation.getName(), toLocation.getName(), travelResponse);
    }


}
