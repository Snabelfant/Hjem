package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

import dag.hjem.Util;

/**
 * Created by Dag on 21.03.2015.
 */
abstract class MovementSection extends Section {
    private DateTime departureTime;
    private DateTime arrivalTime;

    protected MovementSection(DateTime departureTime, DateTime arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public String getDepartureTimeFormatted() {
        return Util.format(departureTime);
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getArrivalTimeFormatted() {
        return Util.format(arrivalTime);
    }

}
