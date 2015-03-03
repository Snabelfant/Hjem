package dag.hjem.model.travelproposal;

import dag.hjem.model.travelproposal.Duration;
import dag.hjem.model.travelproposal.Section;

import static dag.hjem.model.travelproposal.Util.formatDuration;

public class WalkingSection extends Section {
    private Duration walkingTime;

    public WalkingSection(Duration walkingTime) {
        this.walkingTime = walkingTime;
    }

    public Duration getWalkingTime() {
        return walkingTime;
    }

    public String toString() {
        return "  - Gå " + formatDuration(walkingTime) + "\n";
    }
}
