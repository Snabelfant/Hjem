package dag.hjem.model.travelproposal;

import org.joda.time.Duration;

public class WaitingSection extends Section {
    private Duration waitingTime;

    public WaitingSection(Duration waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Duration getWaitingTime() {
        return waitingTime;
    }

    public String toString() {
        return "  - Vent " + waitingTime.getStandardMinutes();
    }
}
