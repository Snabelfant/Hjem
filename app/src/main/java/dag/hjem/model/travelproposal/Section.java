package dag.hjem.model.travelproposal;

import org.joda.time.Duration;

import dag.hjem.ruter.model.Deviation;
import dag.hjem.ruter.model.Stage;

public abstract class Section {

    public static Section fromRuter(Stage ruterStage) {
        if (ruterStage.getTransportation() == dag.hjem.ruter.model.TransportationType.WALKING) {
            Duration walkingTime = ruterStage.getWalkingTime();
            if (walkingTime.getStandardMinutes() == 0) {
                return null;
            } else {
                WalkingSection stage = new WalkingSection(walkingTime,
                        ruterStage.getDepartureTime(),
                        ruterStage.getArrivalTime());
                return stage;
            }
        } else {
            TravelSection stage = new TravelSection(ruterStage.getDepartureTime(), ruterStage.getArrivalTime());
            stage.setDepartureStop(ruterStage.getDepartureStopName(), ruterStage.getDepartureStopId());
            stage.setArrivalStopName(ruterStage.getArrivalStopName());
            stage.setTravelTime(ruterStage.getTravelTime());
            stage.setLine(ruterStage.getLineName(), TransportationType.fromRuter(ruterStage.getTransportation()));
            stage.setDestination(ruterStage.getDestination());

            for (Deviation ruterDeviation : ruterStage.getDeviations()) {
                stage.addDeviation(ruterDeviation.getHeader());
            }

            return stage;
        }
    }

}
