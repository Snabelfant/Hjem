package dag.hjem.model.travelproposal;

public class WalkingSection extends MovementSection {
    private int walkingTime;

    public WalkingSection(int walkingTime, String departureTime, String arrivalTime) {
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
