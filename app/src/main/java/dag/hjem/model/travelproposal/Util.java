package dag.hjem.model.travelproposal;

/**
 * Created by Dag on 24.02.2015.
 */
public class Util {
    public static String toHhMm(String hhkmmkss) {
        if (hhkmmkss == null) {
            return null;
        }

        String[] tokens = hhkmmkss.split(":");
        if (tokens.length != 3) {
            return null;
        }
        return String.format("%s:%s", tokens[0], tokens[1]);
    }

    public static int toMinutes(String hhkmmkss) {
        String hhkmm = toHhMm(hhkmmkss);

        if (hhkmm == null) {
            return 0;
        }

        String[] tokens = hhkmm.split(":");
        if (tokens.length != 2) {
            return 0;
        }
        return Integer.valueOf(tokens[1]);
    }


    public static String formatTime(String fullTime) {
        if (fullTime == null) {
            return null;
        }

        String[] timeTokens = fullTime.split("T");
        return toHhMm(timeTokens[1]);
    }

    public static int timeToMins(String arrivalTime) {
        int hrs = Integer.valueOf(arrivalTime.substring(0, 2));
        int mins = Integer.valueOf(arrivalTime.substring(3, 5));

        return hrs * 60 + mins;

    }


}
