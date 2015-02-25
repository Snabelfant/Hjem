package dag.hjem.model.ruter;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.model.travelproposal.Travel;

public class TravelSearchResult {
    private String from;
    private String to;
    private List<Travel> travelProposals;

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