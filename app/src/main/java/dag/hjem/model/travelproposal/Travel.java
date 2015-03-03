package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dag.hjem.ruter.model.TravelProposal;
import dag.hjem.ruter.model.Stage;

import static dag.hjem.model.travelproposal.Util.formatDuration;
import static dag.hjem.model.travelproposal.Util.formatTime;
import static dag.hjem.model.travelproposal.Util.toDuration;

public class Travel {
    private Date departureTime;
    private Date arrivalTime;
    private Duration totalTravelTime;
    private List<String> remarks;
    private List<Section> sections;


    public static Travel fromRuter(TravelProposal ruterProposal) {
        Travel travel = new Travel();
        travel.departureTime = ruterProposal.getDepartureTime();
        travel.arrivalTime = ruterProposal.getArrivalTime();
        travel.totalTravelTime = toDuration(ruterProposal.getTotalTravelTime());
        travel.remarks = ruterProposal.getRemarks();
        travel.sections = new ArrayList<>();
        for (Stage ruterStage : ruterProposal.getStages()) {
            Section section = Section.fromRuter(ruterStage);
            travel.sections.add(section);
        }

        return travel;
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

        for (Section section : sections) {
            s.append(section.toString());
        }

        return s.toString();
    }
}
