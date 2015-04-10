package dag.hjem;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;

/**
 * Created by Dag on 15.03.2015.
 */
public class Util {
    private static final boolean ISLOG = false;

    public static File getExternalFile(Context context, String filnavn) {
        return new File(context.getExternalFilesDir(null), filnavn);
    }

    public static void log(String s) {
        if (ISLOG) {
            Log.i("Hjem", s);
        }
    }

    public static boolean departureOrArrivalTimesEqual(DateTime time1, DateTime time2) {
        return time1.getDayOfYear() == time2.getDayOfYear() &&
                time1.getHourOfDay() == time2.getHourOfDay() &&
                time1.getMinuteOfHour() == time2.getMinuteOfHour();
    }

    public static Duration toDuration(String hhkmmkss) {
        if (hhkmmkss == null) {
            return null;
        }
        String[] tokens = hhkmmkss.split(":");
        if (tokens.length != 3) {
            return new Duration(0);
        }

        int minutes = Integer.valueOf(tokens[0]) * 60 + Integer.valueOf(tokens[1]);
        return Duration.standardMinutes(minutes);
    }

    public static String format(Duration duration) {
        return duration.getStandardMinutes() + " min";
    }


    public static String formathhmm(Duration duration) {
        long minutes = duration.getStandardMinutes();
        return String.format("(%01d:%02d)", minutes / 60, minutes % 60);
    }

    public static String format(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        return fmt.print(dateTime);
    }


}
