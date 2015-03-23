package dag.hjem.model.travelproposal;

public class WaitingSection extends Section {
    private int waitingTime;

    public WaitingSection(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public String toString() {
        return "  - Vent " + waitingTime;
    }
}
