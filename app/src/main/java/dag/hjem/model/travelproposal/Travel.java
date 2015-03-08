package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dag.hjem.ruter.model.TravelProposal;
import dag.hjem.ruter.model.Stage;

import static dag.hjem.model.travelproposal.Util.formatTime;

public class Travel {
    private String departureTime;
    private String arrivalTime;
    private String totalTravelTime;
    private List<String> remarks;
    private List<Section> sections;


    public static Travel fromRuter(TravelProposal ruterProposal) {
        Travel travel = new Travel();
        travel.departureTime = Util.formatTime(ruterProposal.getDepartureTime());
        travel.arrivalTime = Util.formatTime(ruterProposal.getArrivalTime());
        travel.totalTravelTime = Util.toHhMm(ruterProposal.getTotalTravelTime());
        travel.remarks = ruterProposal.getRemarks();
        travel.sections = new ArrayList<>();
        for (Stage ruterStage : ruterProposal.getStages()) {
            Section section = Section.fromRuter(ruterStage);
            if (section != null) {
                travel.sections.add(section);
            }
        }

        return travel;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("++ ")
                .append(departureTime)
                .append(" - ")
                .append(arrivalTime)
                .append(" (")
                .append(totalTravelTime)
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
