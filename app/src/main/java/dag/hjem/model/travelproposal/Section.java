package dag.hjem.model.travelproposal;

import dag.hjem.ruter.model.Deviation;
import dag.hjem.ruter.model.Stage;

public abstract class Section {

    public static Section fromRuter(Stage ruterStage) {
        if (ruterStage.getTransportation() == dag.hjem.ruter.model.TransportationType.WALKING) {
            int minutes = Util.toMinutes(ruterStage.getWalkingTime());
            if (minutes == 0) {
                return null;
            } else {
                WalkingSection stage = new WalkingSection(minutes,
                        Util.formatTime(ruterStage.getDepartureTime()),
                        Util.formatTime(ruterStage.getArrivalTime()));
                return stage;
            }
        } else {
            TravelSection stage = new TravelSection(Util.formatTime(ruterStage.getDepartureTime()), Util.formatTime(ruterStage.getArrivalTime()));
            stage.setDepartureStop(ruterStage.getDepartureStopName(), ruterStage.getDepartureStopId());
            stage.setArrivalStopName(ruterStage.getArrivalStopName());
            stage.setTravelTime(Util.toHhMm(ruterStage.getTravelTime()));
            stage.setLine(ruterStage.getLineName(), TransportationType.fromRuter(ruterStage.getTransportation()));
            stage.setDestination(ruterStage.getDestination());

            for (Deviation ruterDeviation : ruterStage.getDeviations()) {
                stage.addDeviation(ruterDeviation.getHeader());
            }

            return stage;
        }
    }

}
