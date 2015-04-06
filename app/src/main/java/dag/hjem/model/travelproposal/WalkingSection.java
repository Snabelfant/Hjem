package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

public class WalkingSection extends MovementSection {
    private int walkingTime;

    public WalkingSection(int walkingTime, DateTime departureTime, DateTime arrivalTime) {
        super(departureTime, arrivalTime);
        this.walkingTime = walkingTime;
    }

    public int getWalkingTime() {
        return walkingTime;
    }

    public String toString() {
        return "  - Gå " + walkingTime + " (" + super.getDepartureTime() + " - " + super.getArrivalTime() + ")";
    }
}
