package dag.hjem.model.ruter;

import java.util.ArrayList;
import java.util.List;

public class TravelSearchResult {
    private String from;
    private String to;
    private List<TravelProposalX> travelProposals;

    public static TravelSearchResult fromRuter(String from, String to, TravelResponse ruterResponse) {
        TravelSearchResult travelSearchResult = new TravelSearchResult();
        travelSearchResult.from = from;
        travelSearchResult.to = to;
        travelSearchResult.travelProposals = new ArrayList<>();

        for (TravelProposal travelProposal : ruterResponse.getTravelProposals()) {
            TravelProposalX travelProposalX = TravelProposalX.fromRuter(travelProposal);
            travelSearchResult.travelProposals.add(travelProposalX);
        }

        return travelSearchResult;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("*** " + from + " -> " + to + " ***\n");

        for (TravelProposalX travelProposalX : travelProposals) {
            s.append(travelProposalX.toString());
        }

        return s.toString();
    }
}