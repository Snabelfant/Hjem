package dag.hjem.model.travelproposal;

import dag.hjem.ruter.model.Deviation;
import dag.hjem.ruter.model.TransportationType;
import dag.hjem.ruter.model.Stage;

import static dag.hjem.model.travelproposal.Util.toDuration;

public abstract class Section {

    public static Section fromRuter(Stage ruterStage) {
        if (ruterStage.getTransportation() == TransportationType.WALKING) {
            WalkingSection stage = new WalkingSection(toDuration(ruterStage.getWalkingTime()));
            return stage;
        } else {
            TravelSection stage = new TravelSection();
            stage.setDepartureTime(ruterStage.getDepartureTime());
            stage.setDepartureStopName(ruterStage.getDepartureStopName());
            stage.setArrivalTime(ruterStage.getArrivalTime());
            stage.setArrivalStopName(ruterStage.getArrivalStopName());
            stage.setTravelTime(toDuration(ruterStage.getTravelTime()));
            stage.setTransportation(ruterStage.getTransportation().getDescription());
            stage.setLineName(ruterStage.getLineName());
            stage.setDestination(ruterStage.getDestination());

            for (Deviation ruterDeviation : ruterStage.getDeviations()) {
                stage.addDeviation(ruterDeviation.getHeader());
            }

            return stage;
        }
    }
}
