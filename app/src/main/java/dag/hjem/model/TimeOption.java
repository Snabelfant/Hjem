package dag.hjem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dag on 23.02.2015.
 */
public class TimeOption {
    private int offsetInMinutes;
    private String name;

    public TimeOption(int offsetInMinutes, String name) {
        this.offsetInMinutes = offsetInMinutes;
        this.name = name;
    }

    public Calendar getTime() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, offsetInMinutes);
        return now;
    }

    public String toString() {
        return name;
    }

    public static List<TimeOption> getTimesAfter() {
        List<TimeOption> timeOptions = new ArrayList<>();
        timeOptions.add(new TimeOption(0, "nå"));
        timeOptions.add(new TimeOption(5, "om 5 min."));
        timeOptions.add(new TimeOption(10, "om 10 min."));
        timeOptions.add(new TimeOption(15, "om 15 min."));
        timeOptions.add(new TimeOption(20, "om 20 min."));
        timeOptions.add(new TimeOption(30, "om 30 min."));
        timeOptions.add(new TimeOption(45, "om 30 min."));
        timeOptions.add(new TimeOption(60, "om 1 t."));
        timeOptions.add(new TimeOption(90, "om 1,5 t."));
        timeOptions.add(new TimeOption(120, "om 2 t."));
        timeOptions.add(new TimeOption(180, "om 3 t."));
        return timeOptions;
    }

    public static List<TimeOption> getTimesBefore() {
        List<TimeOption> timeOptions = new ArrayList<>();
        timeOptions.add(new TimeOption(0, "nå"));
        timeOptions.add(new TimeOption(5, "innen 5 min."));
        timeOptions.add(new TimeOption(10, "innen 10 min."));
        timeOptions.add(new TimeOption(15, "innen 15 min."));
        timeOptions.add(new TimeOption(20, "innen 20 min."));
        timeOptions.add(new TimeOption(30, "innen 30 min."));
        timeOptions.add(new TimeOption(45, "innen 30 min."));
        timeOptions.add(new TimeOption(60, "innen 1 t."));
        timeOptions.add(new TimeOption(90, "innen 1,5 t."));
        timeOptions.add(new TimeOption(120, "innen 2 t."));
        timeOptions.add(new TimeOption(180, "innen 3 t."));
        return timeOptions;
    }
}
