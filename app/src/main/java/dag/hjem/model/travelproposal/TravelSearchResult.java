package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.ruter.model.TravelProposal;
import dag.hjem.ruter.model.TravelResponse;

public class TravelSearchResult {
    private String from;
    private String to;
    private List<Travel> travels;
    private String reisError;
    private Exception exception;

    private TravelSearchResult() {
    }

    public static TravelSearchResult fromException(Exception exception) {
        TravelSearchResult travelSearchResult = new TravelSearchResult();
        travelSearchResult.exception = exception;
        return travelSearchResult;
    }

    public static TravelSearchResult fromRuter(String from, String to, boolean isAfter, TravelResponse ruterResponse) {
        TravelSearchResult travelSearchResult = new TravelSearchResult();
        travelSearchResult.from = from;
        travelSearchResult.to = to;
        travelSearchResult.reisError = ruterResponse.getReisError();
        travelSearchResult.travels = new ArrayList<>();

        for (TravelProposal travelProposal : ruterResponse.getTravelProposals()) {
            Travel travel = Travel.fromRuter(travelProposal);

            if (travel.getSummary().getDepartureTime().getTime().isAfterNow()) {
                travelSearchResult.travels.add(travel);
            }
        }

        return travelSearchResult;
    }

    public String getReisError() {
        return reisError;
    }

    public Exception getException() {
        return exception;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("*** " + from + " -> " + to + " ***\n");

        if (travels != null) {
            for (Travel travel : travels) {
                s.append(travel.toString());
            }
        }

        if (exception != null) {
            s.append(exception.toString());
        }
        return s.toString();
    }
}