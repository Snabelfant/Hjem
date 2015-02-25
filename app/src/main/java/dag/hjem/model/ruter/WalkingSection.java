package dag.hjem.model.ruter;

import dag.hjem.model.travelproposal.Section;

import static dag.hjem.model.ruter.Util.formatDuration;

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
