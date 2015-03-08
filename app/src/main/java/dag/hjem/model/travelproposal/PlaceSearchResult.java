package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

public class PlaceSearchResult {
    private Exception exception;
    private List<Place> places = new ArrayList<>();

    public static PlaceSearchResult fromRuter(List<dag.hjem.ruter.model.Place> ruterPlaces) {
        PlaceSearchResult placeSearchResult = new PlaceSearchResult();

        for (dag.hjem.ruter.model.Place ruterPlace : ruterPlaces) {
            placeSearchResult.places.add(Place.fromRuter(ruterPlace));
        }

        return placeSearchResult;
    }

    public static PlaceSearchResult fromException(Exception exception) {
        PlaceSearchResult searchResult = new PlaceSearchResult();
        searchResult.exception = exception;
        return searchResult;
    }

    @Override
    public String toString() {
        return "PlaceSearchResult{" +
                "exception=" + exception +
                ", places=" + placesToString() +
                '}';
    }

    private String placesToString() {
        StringBuilder sb = new StringBuilder();
        for (Place place : places) {
            sb.append(place.toString()).append("\n");
        }

        return sb.toString();

    }
}
