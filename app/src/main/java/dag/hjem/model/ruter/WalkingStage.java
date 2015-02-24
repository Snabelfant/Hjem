package dag.hjem.model.ruter;

import static dag.hjem.model.ruter.Util.formatDuration;

public class WalkingStage extends StageX {
    private Duration walkingTime;

    public String toString() {
        return "  - Gå " + formatDuration(walkingTime) + "\n";
    }
}
