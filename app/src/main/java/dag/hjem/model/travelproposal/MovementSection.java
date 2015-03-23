package dag.hjem.model.travelproposal;

/**
 * Created by Dag on 21.03.2015.
 */
abstract class MovementSection extends Section {
    private String departureTime;
    private String arrivalTime;

    protected MovementSection(String departureTime, String arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

}
