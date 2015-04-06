package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.ruter.model.Stage;
import dag.hjem.ruter.model.TravelProposal;

public class Travel {
    private Summary summary;
    private List<Section> sections;

    public static Travel fromRuter(TravelProposal ruterProposal) {
        Travel travel = new Travel();
        travel.sections = new ArrayList<>();

        Section previousSection = null;
        for (Stage ruterStage : ruterProposal.getStages()) {
            Section section = Section.fromRuter(ruterStage);

            if (section != null) {
                WaitingSection waitingSection = createWaitingSection(previousSection, section);

                if (waitingSection != null) {
                    travel.sections.add(waitingSection);
                }

                travel.sections.add(section);
                previousSection = section;
            }
        }

        String totalTravelTime = Util.toHhMm(ruterProposal.getTotalTravelTime());
        List<String> remarks = ruterProposal.getRemarks();
        travel.summary = new Summary(ruterProposal.getDepartureTime(), ruterProposal.getArrivalTime(), totalTravelTime, travel.sections, remarks);
        return travel;
    }

    private static WaitingSection createWaitingSection(Section previousSection, Section thisSection) {
        WaitingSection waitingSection = null;

        if (previousSection instanceof MovementSection && thisSection instanceof MovementSection) {
            DateTime thisDepartureTime = ((MovementSection) thisSection).getDepartureTime();
            DateTime previousArrivalTime = ((MovementSection) previousSection).getArrivalTime();
            int waitingTime = Minutes.minutesBetween(previousArrivalTime, thisDepartureTime).getMinutes();
            if (waitingTime > 0) {
                waitingSection = new WaitingSection(waitingTime);
            }
        }
        return waitingSection;
    }


    public Summary getSummary() {
        return summary;
    }

    public List<Section> getSections() {
        return sections;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(summary.toString() + "\n");
        for (Section section : sections) {
            s.append(section.toString() + "\n");
        }

        return s.toString();
    }
}
