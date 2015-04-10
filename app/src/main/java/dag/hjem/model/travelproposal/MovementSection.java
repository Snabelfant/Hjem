package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

/**
 * Created by Dag on 21.03.2015.
 */
abstract class MovementSection extends Section {
    private DepartureOrArrivalTime departureTime;
    private DepartureOrArrivalTime arrivalTime;

    protected MovementSection(DateTime departureTime, DateTime arrivalTime) {
        this.departureTime = new DepartureOrArrivalTime(departureTime);
        this.arrivalTime = new DepartureOrArrivalTime(arrivalTime);
    }

    public DepartureOrArrivalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DepartureOrArrivalTime departureTime) {
        this.departureTime = departureTime;
    }

    public DepartureOrArrivalTime getArrivalTime() {
        return arrivalTime;
    }
}
