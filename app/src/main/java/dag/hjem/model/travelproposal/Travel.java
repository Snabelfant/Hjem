package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.Util;
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

        List<String> remarks = ruterProposal.getRemarks();
        travel.summary = new Summary(ruterProposal.getDepartureTime(), ruterProposal.getArrivalTime(), ruterProposal.getTotalTravelTime(), travel.sections, remarks);
        return travel;
    }

    private static WaitingSection createWaitingSection(Section previousSection, Section thisSection) {
        WaitingSection waitingSection = null;

        if (previousSection instanceof MovementSection && thisSection instanceof MovementSection) {
            DepartureOrArrivalTime thisDepartureTime = ((MovementSection) thisSection).getDepartureTime();
            DepartureOrArrivalTime previousArrivalTime = ((MovementSection) previousSection).getArrivalTime();
            Duration waitingTime = new Duration(previousArrivalTime.getTime(), thisDepartureTime.getTime());
            if (waitingTime.getStandardMinutes() > 0) {
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

    public boolean setRealtime(String lineNo, int ruterStopId, RealtimeCall realtimeCall) {
        boolean resultWasUsed = false;
        boolean isFirstTravelSection = true;

        for (Section section : sections) {
            if (section instanceof TravelSection) {
                TravelSection travelSection = (TravelSection) section;

                boolean isMatchingSection =
                        lineNo.equals(travelSection.getLine().getLineName()) &&
                                ruterStopId == travelSection.getDepartureStopId() &&
                                travelSection.getDestination().equals(realtimeCall.getDestinationName());

                if (isMatchingSection) {
                    DateTime aimedDepartureTime = realtimeCall.getAimedDepartureTime();
                    DateTime scheduledDeparture = travelSection.getDepartureTime().getTime();
                    if (Util.departureOrArrivalTimesEqual(aimedDepartureTime, scheduledDeparture)) {
                        Util.log("FUNNET=" + travelSection.getArrivalStopName() + ":" + realtimeCall.getExpectedDepartureTime().toString() + "/" + aimedDepartureTime.toString() + "/" + scheduledDeparture.toString());
                        travelSection.setRealtimeDepartureTime(realtimeCall.getExpectedDepartureTime());

                        if (isFirstTravelSection) {
                            summary.setRealtimeDepartureTime(realtimeCall.getExpectedDepartureTime());
                        }
                        resultWasUsed = true;
                    }
                }

                isFirstTravelSection = false;
            }
        }

        return resultWasUsed;
    }
}
