package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class WalkingSection extends MovementSection {
    private Duration walkingTime;

    public WalkingSection(Duration walkingTime, DateTime departureTime, DateTime arrivalTime) {
        super(departureTime, arrivalTime);
        this.walkingTime = walkingTime;
    }

    public Duration getWalkingTime() {
        return walkingTime;
    }

    public String toString() {
        return "  - Gå " + walkingTime.getStandardMinutes() + " (" + super.getDepartureTime() + " - " + super.getArrivalTime() + ")";
    }
}
