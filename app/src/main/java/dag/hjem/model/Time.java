package dag.hjem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Dag on 23.02.2015.
 */
public class Time {
    private int offsetInMinutes;
    private String name;

    public Time(int offsetInMinutes, String name) {
        this.offsetInMinutes = offsetInMinutes;
        this.name = name;
    }

    public Calendar getTime() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, offsetInMinutes);
        return now;
    }

    public static List<Time> getTimes() {
        List<Time> times = new ArrayList<>();
        times.add(new Time(0, "Nå"));
        times.add(new Time(5, "Om 5 min."));
        times.add(new Time(30, "Om 30 min."));
        times.add(new Time(60, "Om 1 t."));
        times.add(new Time(90, "Om 1,5 t."));
        times.add(new Time(120, "Om 2 t."));
        return times;
    }
}
