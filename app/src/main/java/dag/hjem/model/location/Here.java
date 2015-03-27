package dag.hjem.model.location;

import dag.hjem.gps.GpsObserver;
import dag.hjem.gps.UtmPosition;

/**
 * Created by Dag on 22.02.2015.
 */
public class Here extends UtmLocation {
    private static UtmPosition currentPosition = null;
    private static final GpsObserver gpsObserver = new GpsObserver() {
        @Override
        public void positionChanged(UtmPosition position) {
            currentPosition = position;
        }
    };

    public Here(String name) {
        super(name, 0, 0);


    }

    public static UtmPosition getCurrentPosition() {
        return currentPosition;
    }

    public static GpsObserver getGpsObserver() {
        return gpsObserver;
    }

    @Override
    public int getX() {
        checkPosition();
        return currentPosition.getUtmEast();
    }

    @Override
    public int getY() {
        checkPosition();
        return currentPosition.getUtmNorth();
    }

    private void checkPosition() {
        if (currentPosition == null) {
            throw new RuntimeException("Du må slå på GPS");
        }
    }
}
