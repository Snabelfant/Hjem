package dag.hjem.model.ruter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dag.hjem.model.ruter.Util.formatDuration;
import static dag.hjem.model.ruter.Util.formatTime;
import static dag.hjem.model.ruter.Util.toDuration;

public class TravelProposalX {
    private Date departureTime;
    private Date arrivalTime;
    private Duration totalTravelTime;
    private List<String> remarks;
    private List<StageX> stages;


    public static TravelProposalX fromRuter(TravelProposal ruterProposal) {
        TravelProposalX travelProposalX = new TravelProposalX();
        travelProposalX.departureTime = ruterProposal.getDepartureTime();
        travelProposalX.arrivalTime = ruterProposal.getArrivalTime();
        travelProposalX.totalTravelTime = toDuration(ruterProposal.totalTravelTime);
        travelProposalX.remarks = ruterProposal.getRemarks();
        travelProposalX.stages = new ArrayList<>();
        for (Stage ruterStage : ruterProposal.getStages()) {
            StageX stageX = StageX.fromRuter(ruterStage);
            travelProposalX.stages.add(stageX);
        }

        return travelProposalX;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("++ ")
                .append(formatTime(departureTime))
                .append(" - ")
                .append(formatTime(arrivalTime))
                .append(" (")
                .append(formatDuration(totalTravelTime))
                .append(")\n");

        for (String remark : remarks) {
            s.append("     ! ").append(remark).append("\n");
        }

        for (StageX stage : stages) {
            s.append(stage.toString());
        }

        return s.toString();
    }
}
