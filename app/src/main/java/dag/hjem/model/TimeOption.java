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
        timeOptions.add(new TimeOption(AFTER, 5, "om 5 min."));
        timeOptions.add(new TimeOption(AFTER, 10, "om 10 min."));
        timeOptions.add(new TimeOption(AFTER, 15, "om 15 min."));
        timeOptions.add(new TimeOption(AFTER, 20, "om 20 min."));
        timeOptions.add(new TimeOption(AFTER, 30, "om 30 min."));
        timeOptions.add(new TimeOption(AFTER, 45, "om 45 min."));
        timeOptions.add(new TimeOption(AFTER, 60, "om 1 t."));
        timeOptions.add(new TimeOption(AFTER, 90, "om 1,5 t."));
        timeOptions.add(new TimeOption(AFTER, 120, "om 2 t."));
        timeOptions.add(new TimeOption(AFTER, 180, "om 3 t."));
        timeOptions.add(new TimeOption(BEFORE, 0, "nå"));
        timeOptions.add(new TimeOption(BEFORE, 5, "om 5 min."));
        timeOptions.add(new TimeOption(BEFORE, 10, "om 10 min."));
        timeOptions.add(new TimeOption(BEFORE, 15, "om 15 min."));
        timeOptions.add(new TimeOption(BEFORE, 20, "om 20 min."));
        timeOptions.add(new TimeOption(BEFORE, 30, "om 30 min."));
        timeOptions.add(new TimeOption(BEFORE, 45, "om 45 min."));
        timeOptions.add(new TimeOption(BEFORE, 60, "om 1 t."));
        timeOptions.add(new TimeOption(BEFORE, 90, "om 1,5 t."));
        timeOptions.add(new TimeOption(BEFORE, 120, "om 2 t."));
        timeOptions.add(new TimeOption(BEFORE, 180, "om 3 t."));
        return timeOptions;
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
