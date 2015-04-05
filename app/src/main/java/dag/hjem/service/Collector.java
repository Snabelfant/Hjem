package dag.hjem.service;

import dag.hjem.model.travelproposal.HouseSearchResult;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.RealtimeSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;

public class Collector {

    public void setTravelSearchResult(TravelSearchResult result) {
        throw new RuntimeException("setTravelSearchResult ikke implementert");
    }

    public void setPlaceSearchResult(PlaceSearchResult result) {
        throw new RuntimeException("setPlaceSearchResult ikke implementert");
    }


    public void setHouseSearchResult(HouseSearchResult result) {
        throw new RuntimeException("setHouseSearchResult ikke implementert");
    }

    public void setRealtimeSearchResult(RealtimeSearchResult realtimeSearchResult) {
        throw new RuntimeException("setRealtimeSearchResult ikke implementert");
    }
}
