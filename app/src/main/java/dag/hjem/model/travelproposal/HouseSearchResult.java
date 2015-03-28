package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dag on 28.03.2015.
 */
public class HouseSearchResult {
    private Exception exception;
    private List<House> houses = new ArrayList<>();

    public static HouseSearchResult fromRuter(List<dag.hjem.ruter.model.House> ruterHouses) {
        HouseSearchResult searchResult = new HouseSearchResult();

        for (dag.hjem.ruter.model.House ruterHouse : ruterHouses) {
            searchResult.houses.add(House.fromRuter(ruterHouse));
        }

        return searchResult;
    }

    public static HouseSearchResult fromException(Exception exception) {
        HouseSearchResult searchResult = new HouseSearchResult();
        searchResult.exception = exception;
        return searchResult;
    }

    public Exception getException() {
        return exception;
    }

    public List<House> getHouses() {
        return houses;
    }

}
