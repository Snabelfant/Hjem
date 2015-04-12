package dag.hjem.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static dag.hjem.model.TimeDirection.AFTER;
import static dag.hjem.model.TimeDirection.BEFORE;


public class TimeOption {
    private TimeDirection timeDirection;
    private int offsetInHours;
    private DateTime departureOrArrivalTime;
    private String name;

    private TimeOption(TimeDirection timeDirection, DateTime departureOrArrivalTime, String name) {
        this.timeDirection = timeDirection;
        this.departureOrArrivalTime = departureOrArrivalTime;
        this.name = name;
    }

    public static List<TimeOption> getTimes() {
        List<TimeOption> timeOptions = new ArrayList<>();
        timeOptions.add(new TimeOption(AFTER, DateTime.now(), "nå"));

        DateTime thisHour = DateTime.now().plusHours(1).withMinuteOfHour(0).withSecondOfMinute(0);

        for (int hours = 1; hours <= 6; hours++) {
            DateTime departureTime = thisHour.plusHours(hours);
            String asText = departureTime.getHourOfDay() + ":00";
            timeOptions.add(new TimeOption(AFTER, departureTime, asText));
        }

        for (int hours = 1; hours <= 6; hours++) {
            DateTime arrivalTime = thisHour.plusHours(hours);
            String asText = arrivalTime.getHourOfDay() + ":00";
            timeOptions.add(new TimeOption(BEFORE, arrivalTime, asText));
        }
        return timeOptions;
    }

    public TimeDirection getTimeDirection() {
        return timeDirection;
    }

    public DateTime getTime() {
        return departureOrArrivalTime;
    }

    public String toString() {
        return timeDirection.toString() + " " + name;
    }

}
