package dag.hjem.model.travelproposal;

import dag.hjem.ruter.model.Deviation;
import dag.hjem.ruter.model.TransportationType;
import dag.hjem.ruter.model.Stage;

public abstract class Section {

    public static Section fromRuter(Stage ruterStage) {
        if (ruterStage.getTransportation() == TransportationType.WALKING) {
            int minutes = Util.toMinutes(ruterStage.getWalkingTime());
            if (minutes == 0) {
                return null;
            } else {
                WalkingSection stage = new WalkingSection(minutes);
                return stage;
            }
        } else {
            TravelSection stage = new TravelSection();
            stage.setDepartureTime(Util.formatTime(ruterStage.getDepartureTime()));
            stage.setDepartureStopName(ruterStage.getDepartureStopName());
            stage.setArrivalTime(Util.formatTime(ruterStage.getArrivalTime()));
            stage.setArrivalStopName(ruterStage.getArrivalStopName());
            stage.setTravelTime(Util.toHhMm(ruterStage.getTravelTime()));
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
