package dag.hjem.model.travelproposal;

public class WalkingSection extends Section {
    private int walkingTime;

    public WalkingSection(int walkingTime) {
        this.walkingTime = walkingTime;
    }

    public int getWalkingTime() {
        return walkingTime;
    }

    public String toString() {
        return "  - Gå " + walkingTime;
    }
}
