package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.ruter.model.TravelProposal;
import dag.hjem.ruter.model.TravelResponse;

public class TravelSearchResult {
    private String from;
    private String to;
    private List<Travel> travelProposals;
    private Exception exception;

    public static TravelSearchResult fromException(Exception exception) {
        TravelSearchResult travelSearchResult = new TravelSearchResult();
        travelSearchResult.exception = exception;
        return travelSearchResult;
    }

    public static TravelSearchResult fromRuter(String from, String to, TravelResponse ruterResponse) {
        TravelSearchResult travelSearchResult = new TravelSearchResult();
        travelSearchResult.from = from;
        travelSearchResult.to = to;
        travelSearchResult.travelProposals = new ArrayList<>();

        for (TravelProposal travelProposal : ruterResponse.getTravelProposals()) {
            Travel travel = Travel.fromRuter(travelProposal);
            travelSearchResult.travelProposals.add(travel);
        }

        return travelSearchResult;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("*** " + from + " -> " + to + " ***\n");

        for (Travel travel : travelProposals) {
            s.append(travel.toString());
        }

        return s.toString();
    }
}