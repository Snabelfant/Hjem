package posisjon;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Dag
 * Date: 22.06.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class Posisjon extends Location {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm.ss");
    private Konvertering.UtmPosisjon utmPosisjon;

    public Posisjon(Location posisjonGrader) {
        super(posisjonGrader);
        utmPosisjon = Konvertering.latLonToUtm(posisjonGrader);
    }

    public int getUtmEast() {
        return utmPosisjon.getUtmØst();
    }

    public int getUtmNorth() {
        return utmPosisjon.getUtmNord();
    }

    @Override
    public String toString() {
        String time = timeFormat.format(new Date(getTime()));
        return time + ": " + utmPosisjon.getUtmØst() + "/" + utmPosisjon.getUtmNord();
    }
}
