package gps;


import android.location.Location;

/**
 * Created by Dag on 23.02.2015.
 */
public interface GpsObserver {
    void positionChanged(UtmPosition position);

}
