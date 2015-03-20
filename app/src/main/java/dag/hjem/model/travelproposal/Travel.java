package dag.hjem.model.travelproposal;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.ruter.model.Stage;
import dag.hjem.ruter.model.TravelProposal;

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

    public List<Section> getSections() {
        return sections;
    }

    public String getSummary() {
        return new StringBuilder().append("++ ")
                .append(departureTime)
                .append(" - ")
                .append(arrivalTime)
                .append(" (")
                .append(totalTravelTime)
                .append(")\n").toString();


    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(getSummary() + "\n");
        for (String remark : remarks) {
            s.append("     ! ").append(remark).append("\n");
        }

        for (Section section : sections) {
            s.append(section.toString() + "\n");
        }

        return s.toString();
    }
}
