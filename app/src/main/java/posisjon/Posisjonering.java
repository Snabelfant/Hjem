package posisjon;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Dag
 * Date: 20.06.13
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
public class Posisjonering {
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private Posisjon bestePosisjonHittil = null;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private final TextView textView;

    public Posisjonering(Context context, TextView textView) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.textView = textView;
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                int secondsSinceLastBestPositionFound = 0;
                if (isBetterLocation(location, bestePosisjonHittil)) {
                    bestePosisjonHittil = new Posisjon(location);
                } else {
                    secondsSinceLastBestPositionFound = (int) ((location.getTime() - bestePosisjonHittil.getTime())/1000);
                }

                Posisjonering.this.textView.setText(bestePosisjonHittil.toString() + " (" + secondsSinceLastBestPositionFound + ")");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        Location sisteKjente = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (sisteKjente != null) {
            bestePosisjonHittil = new Posisjon(sisteKjente);
            Posisjonering.this.textView.setText(bestePosisjonHittil.toString());
        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }


    private boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


}
