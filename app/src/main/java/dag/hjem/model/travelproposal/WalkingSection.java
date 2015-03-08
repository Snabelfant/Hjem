package dag.hjem.model.travelproposal;

import dag.hjem.model.travelproposal.Section;

public class WalkingSection extends Section {
    private int walkingTime;

    public WalkingSection(int walkingTime) {
        this.walkingTime = walkingTime;
    }

    public int getWalkingTime() {
        return walkingTime;
    }

    public String toString() {
        return "  - Gå " + walkingTime + "\n";
    }
}
