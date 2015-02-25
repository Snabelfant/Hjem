package dag.hjem.model.travelproposal;

import dag.hjem.model.ruter.Deviation;
import dag.hjem.model.ruter.TransportationType;
import dag.hjem.model.ruter.TravelSection;
import dag.hjem.model.ruter.WalkingSection;

import static dag.hjem.model.ruter.Util.toDuration;

public abstract class Section {

    public static Section fromRuter(dag.hjem.model.ruter.Stage ruterStage) {
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
