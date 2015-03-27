package dag.hjem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dag.hjem.model.TimeDirection.AFTER;
import static dag.hjem.model.TimeDirection.BEFORE;

/**
 * Created by Dag on 23.02.2015.
 */
public class TimeOption {
    private TimeDirection timeDirection;
    private int offsetInMinutes;
    private String name;

    public TimeOption(TimeDirection timeDirection, int offsetInMinutes, String name) {
        this.timeDirection = timeDirection;
        this.offsetInMinutes = offsetInMinutes;
        this.name = name;
    }

    public static List<TimeOption> getTimes() {
        List<TimeOption> timeOptions = new ArrayList<>();
        timeOptions.add(new TimeOption(AFTER, 0, "nå"));
        timeOptions.add(new TimeOption(AFTER, 5, "5 min."));
        timeOptions.add(new TimeOption(AFTER, 10, "10 min."));
        timeOptions.add(new TimeOption(AFTER, 15, "15 min."));
        timeOptions.add(new TimeOption(AFTER, 20, "20 min."));
        timeOptions.add(new TimeOption(AFTER, 30, "30 min."));
        timeOptions.add(new TimeOption(AFTER, 45, "45 min."));
        timeOptions.add(new TimeOption(AFTER, 60, "1 t."));
        timeOptions.add(new TimeOption(AFTER, 90, "1,5 t."));
        timeOptions.add(new TimeOption(AFTER, 120, "2 t."));
        timeOptions.add(new TimeOption(AFTER, 180, "3 t."));
        timeOptions.add(new TimeOption(BEFORE, 0, "nå"));
        timeOptions.add(new TimeOption(BEFORE, 5, "5 min."));
        timeOptions.add(new TimeOption(BEFORE, 10, "10 min."));
        timeOptions.add(new TimeOption(BEFORE, 15, "15 min."));
        timeOptions.add(new TimeOption(BEFORE, 20, "20 min."));
        timeOptions.add(new TimeOption(BEFORE, 30, "30 min."));
        timeOptions.add(new TimeOption(BEFORE, 45, "45 min."));
        timeOptions.add(new TimeOption(BEFORE, 60, "1 t."));
        timeOptions.add(new TimeOption(BEFORE, 90, "1,5 t."));
        timeOptions.add(new TimeOption(BEFORE, 120, "2 t."));
        timeOptions.add(new TimeOption(BEFORE, 180, "3 t."));
        return timeOptions;
    }

    public TimeDirection getTimeDirection() {
        return timeDirection;
    }

    public Calendar getTime() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, offsetInMinutes);
        return now;
    }

    public String toString() {
        return timeDirection.toString() + " " + name;
    }

}
