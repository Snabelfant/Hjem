package dag.hjem.model.travelproposal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dag on 24.02.2015.
 */
public class Util {
    public static Duration toDuration(String s) {
        if (s == null) {
            return null;
        }

        String[] tokens = s.split(":");
        if (tokens.length != 3) {
            return null;
        }
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        return Duration.ofMinutes(60 * hours + minutes);
    }

    public static String formatDuration(Duration d) {
        long minutes = d.toMinutes();
        return String.format("%d:%02d", minutes / 60, minutes % 60);
    }

    public static String formatTime(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

}
