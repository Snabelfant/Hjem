package dag.hjem.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static dag.hjem.model.TimeDirection.AFTER;
import static dag.hjem.model.TimeDirection.BEFORE;

/**
 * Created by Dag on 23.02.2015.
 */
public class TimeOption {
    private TimeDirection timeDirection;
    private int offsetInHours;
    private String name;

    public TimeOption(TimeDirection timeDirection, int offsetInHours, String name) {
        this.timeDirection = timeDirection;
        this.offsetInHours = offsetInHours;
        this.name = name;
    }

    public static List<TimeOption> getTimes() {
        List<TimeOption> timeOptions = new ArrayList<>();
        for (int hours = 0; hours <= 24; hours++) {
            String asText = hours == 0 ? "nå" : hours + "t";
            timeOptions.add(new TimeOption(AFTER, hours, asText));
        }

        for (int hours = 0; hours <= 24; hours++) {
            String asText = hours == 0 ? "nå" : hours + "t";
            timeOptions.add(new TimeOption(BEFORE, hours, asText));
        }
        return timeOptions;
    }

    public TimeDirection getTimeDirection() {
        return timeDirection;
    }

    public DateTime getTime() {
        return DateTime.now().plusHours(offsetInHours);
    }

    public String toString() {
        return timeDirection.toString() + " " + name;
    }

}
