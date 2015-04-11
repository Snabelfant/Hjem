package dag.hjem.service;

import org.joda.time.DateTime;

import java.io.IOException;

import dag.hjem.model.location.Location;

public class TravelService {


    public void getHouses(int ruterStreetId, Collector collector) throws IOException {
        GetHouses getHouses = new GetHouses(collector);
        getHouses.getHouses(ruterStreetId);
    }

    public void getPlaces(String partialName, Collector collector) throws IOException {
        GetPlaces getPlaces = new GetPlaces(collector);
        getPlaces.getPlaces(partialName);
    }

    public void getTravelProposals(Location fromLocation, Location toLocation, boolean isAfter, DateTime departureOrArrivalTime, Collector collector) throws IOException {
        GetTravelProposals getTravelProposals = new GetTravelProposals(collector);
        getTravelProposals.getTravelProposals(fromLocation, toLocation, isAfter, departureOrArrivalTime);
    }

}
