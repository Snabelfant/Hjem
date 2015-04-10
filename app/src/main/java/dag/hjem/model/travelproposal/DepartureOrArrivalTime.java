package dag.hjem.model.travelproposal;

import org.joda.time.DateTime;

import dag.hjem.Util;

/**
 * Created by Dag on 08.04.2015.
 */
public class DepartureOrArrivalTime {
    private DateTime time;
    private boolean isRealtime;

    public DepartureOrArrivalTime(DateTime scheduledTime) {
        this.time = scheduledTime;
        this.isRealtime = false;
    }

    public DepartureOrArrivalTime(DateTime realTime, boolean isRealtime) {
        this.time = realTime;
        this.isRealtime = isRealtime;
    }

    public DateTime getTime() {
        return time;
    }

    public boolean isRealtime() {
        return isRealtime;
    }

    public String format() {
        return Util.format(time);
    }
}
