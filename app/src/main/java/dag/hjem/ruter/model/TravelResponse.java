package dag.hjem.ruter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TravelResponse {
    private List<TravelProposal> travelProposals;
    private ReisError reisError;

    public TravelResponse(@JsonProperty("TravelProposals") List<TravelProposal> travelProposals, @JsonProperty("ReisError") ReisError reisError) {
        this.travelProposals = travelProposals;
        this.reisError = reisError;
    }

    public List<TravelProposal> getTravelProposals() {
        return travelProposals;
    }

    public ReisError getReisError() {
        return reisError;
    }

    @Override
    public String toString() {
        return "TravelResponse{" +
                "travelProposals=" + travelProposals +
                ", reisError=" + reisError +
                '}';
    }
}
