package dag.hjem.model.ruter;

import java.util.ArrayList;

import static dag.hjem.model.ruter.Util.toDuration;

public abstract class StageX {

    public static StageX fromRuter(Stage ruterStage) {
        if (ruterStage.getTransportation() == TransportationType.WALKING) {
            WalkingStage stage = new WalkingStage();
            stage.walkingTime = toDuration(ruterStage.getWalkingTime());
            return stage;
        } else {
            TravelStage stage = new TravelStage();
            stage.departureTime = ruterStage.getDepartureTime();
            stage.departureStopName = ruterStage.getDepartureStopName();
            stage.arrivalTime = ruterStage.getArrivalTime();
            stage.arrivalStopName = ruterStage.getArrivalStopName();
            stage.travelTime = toDuration(ruterStage.getTravelTime());
            stage.transportation = ruterStage.getTransportation().getDescription();
            stage.lineName = ruterStage.getLineName();
            stage.destination = ruterStage.getDestination();
            stage.deviations = new ArrayList<>();

            for (Deviation ruterDeviation : ruterStage.getDeviations()) {
                stage.deviations.add(ruterDeviation.getHeader());
            }

            return stage;
        }
    }
}
