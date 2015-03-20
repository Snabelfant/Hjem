package dag.hjem.service;

import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;

/**
 * Created by Dag on 16.03.2015.
 */
public interface TravelServiceCollector {
    public void setTravelSearchResult(TravelSearchResult result);

    void setPlaceSearchResult(PlaceSearchResult result);

}
