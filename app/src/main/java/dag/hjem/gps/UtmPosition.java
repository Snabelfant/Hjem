package dag.hjem.gps;

import android.location.Location;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtmPosition implements Serializable {
    private static SimpleDateFormat TIME = new SimpleDateFormat("HH:mm:ss");
    private int utmNorth;
    private int utmEast;
    private String zone;
    private long timeStamp;

    public UtmPosition(long timeStamp, String zone, int utmEast, int utmNorth) {
        this.utmNorth = utmNorth;
        this.utmEast = utmEast;
        this.zone = zone;
        this.timeStamp = timeStamp;
    }

    public static UtmPosition fromLatLon(Location location) {
        return location == null ? null : latLonToUtm(location.getTime(), location.getLatitude(), location.getLongitude());
    }

    private static UtmPosition latLonToUtm(long timeStamp, double latitude, double longitude) {
        LatLon2UTM c = new LatLon2UTM();
        return c.convertLatLonToUTM(timeStamp, latitude, longitude);

    }

    private static double degreeToRadian(double degree) {
        return degree * Math.PI / 180;
    }

    private static double POW(double a, double b) {
        return Math.pow(a, b);
    }

    private static double SIN(double value) {
        return Math.sin(value);
    }

    private static double COS(double value) {
        return Math.cos(value);
    }

    private static double TAN(double value) {
        return Math.tan(value);
    }

    @Override
    public String toString() {
        return zone + "/" + utmEast + "/" + utmNorth + " " + getTime();
    }

    public int getUtmNorth() {
        return utmNorth;
    }

    public int getUtmEast() {
        return utmEast;
    }

    public String getTime() {
        return TIME.format(new Date(timeStamp));
    }

    private static class LatLon2UTM {
        // equatorial radius
        double equatorialRadius = 6378137;
        // polar radius
        double polarRadius = 6356752.314;
        // flattening
        double flattening = 0.00335281066474748;// (equatorialRadius-polarRadius)/equatorialRadius;
        // inverse flattening 1/flattening
        double inverseFlattening = 298.257223563;// 1/flattening;
        // Mean radius
        double rm = POW(equatorialRadius * polarRadius, 1 / 2.0);
        // scale factor
        double k0 = 0.9996;

        // Lat Lon to UTM variables
        // eccentricity
        double e = Math.sqrt(1 - POW(polarRadius / equatorialRadius, 2));
        double e1sq = e * e / (1 - e * e);
        double n = (equatorialRadius - polarRadius)
                / (equatorialRadius + polarRadius);
        // r curv 1
        double rho = 6368573.744;
        // r curv 2
        double nu = 6389236.914;
        // Calculate Meridional Arc Length
        // Meridional Arc
        double S = 5103266.421;
        double A0 = 6367449.146;
        double B0 = 16038.42955;
        double C0 = 16.83261333;
        double D0 = 0.021984404;
        double E0 = 0.000312705;
        // Calculation Constants
        // Delta Long
        double p = -0.483084;
        double sin1 = 4.84814E-06;
        // Coefficients for UTM Coordinates
        double K1 = 5101225.115;
        double K2 = 3750.291596;
        double K3 = 1.397608151;
        double K4 = 214839.3105;
        double K5 = -2.995382942;
        double A6 = -1.00541E-07;

        public UtmPosition convertLatLonToUTM(long timeStamp, double latitude, double longitude) {
            validate(latitude, longitude);

            setVariables(latitude, longitude);

            String longZone = getLongZone(longitude);
            LatZones latZones = new LatZones();
            String latZone = latZones.getLatZone(latitude);

            double _easting = getEasting();
            double _northing = getNorthing(latitude);

            UtmPosition utmPosition = new UtmPosition(timeStamp, longZone + latZone, (int) _easting, (int) _northing);

            return utmPosition;

        }

        private void validate(double latitude, double longitude) {
            if (latitude < -90.0 || latitude > 90.0 || longitude < -180.0
                    || longitude >= 180.0) {
                throw new IllegalArgumentException(
                        "Legal ranges: latitude [-90,90], longitude [-180,180).");
            }

        }

        protected void setVariables(double latitude, double longitude) {
            latitude = degreeToRadian(latitude);
            rho = equatorialRadius * (1 - e * e)
                    / POW(1 - POW(e * SIN(latitude), 2), 3 / 2.0);

            nu = equatorialRadius / POW(1 - POW(e * SIN(latitude), 2), (1 / 2.0));

            double var1;
            if (longitude < 0.0) {
                var1 = ((int) ((180 + longitude) / 6.0)) + 1;
            } else {
                var1 = ((int) (longitude / 6)) + 31;
            }
            double var2 = (6 * var1) - 183;
            double var3 = longitude - var2;
            p = var3 * 3600 / 10000;

            S = A0 * latitude - B0 * SIN(2 * latitude) + C0 * SIN(4 * latitude) - D0
                    * SIN(6 * latitude) + E0 * SIN(8 * latitude);

            K1 = S * k0;
            K2 = nu * SIN(latitude) * COS(latitude) * POW(sin1, 2) * k0 * (100000000)
                    / 2;
            K3 = ((POW(sin1, 4) * nu * SIN(latitude) * Math.pow(COS(latitude), 3)) / 24)
                    * (5 - POW(TAN(latitude), 2) + 9 * e1sq * POW(COS(latitude), 2) + 4
                    * POW(e1sq, 2) * POW(COS(latitude), 4))
                    * k0
                    * (10000000000000000L);

            K4 = nu * COS(latitude) * sin1 * k0 * 10000;

            K5 = POW(sin1 * COS(latitude), 3) * (nu / 6)
                    * (1 - POW(TAN(latitude), 2) + e1sq * POW(COS(latitude), 2)) * k0
                    * 1000000000000L;

            A6 = (POW(p * sin1, 6) * nu * SIN(latitude) * POW(COS(latitude), 5) / 720)
                    * (61 - 58 * POW(TAN(latitude), 2) + POW(TAN(latitude), 4) + 270
                    * e1sq * POW(COS(latitude), 2) - 330 * e1sq
                    * POW(SIN(latitude), 2)) * k0 * (1E+24);

        }

        protected String getLongZone(double longitude) {
            double longZone = 0;
            if (longitude < 0.0) {
                longZone = ((180.0 + longitude) / 6) + 1;
            } else {
                longZone = (longitude / 6) + 31;
            }
            String val = String.valueOf((int) longZone);
            if (val.length() == 1) {
                val = "0" + val;
            }
            return val;
        }

        protected double getNorthing(double latitude) {
            double northing = K1 + K2 * p * p + K3 * POW(p, 4);
            if (latitude < 0.0) {
                northing = 10000000 + northing;
            }
            return northing;
        }

        protected double getEasting() {
            return 500000 + (K4 * p + K5 * POW(p, 3));
        }

    }

    private static class LatZones {
        private char[] letters = {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
                'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Z'};

        private int[] degrees = {-90, -84, -72, -64, -56, -48, -40, -32, -24, -16,
                -8, 0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 84};

        private char[] negLetters = {'A', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
                'L', 'M'};

        private int[] negDegrees = {-90, -84, -72, -64, -56, -48, -40, -32, -24,
                -16, -8};

        private char[] posLetters = {'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Z'};

        private int[] posDegrees = {0, 8, 16, 24, 32, 40, 48, 56, 64, 72, 84};

        private int arrayLength = 22;

        public LatZones() {
        }

        public int getLatZoneDegree(String letter) {
            char ltr = letter.charAt(0);
            for (int i = 0; i < arrayLength; i++) {
                if (letters[i] == ltr) {
                    return degrees[i];
                }
            }
            return -100;
        }

        public String getLatZone(double latitude) {
            int latIndex = -2;
            int lat = (int) latitude;

            if (lat >= 0) {
                int len = posLetters.length;
                for (int i = 0; i < len; i++) {
                    if (lat == posDegrees[i]) {
                        latIndex = i;
                        break;
                    }

                    if (lat > posDegrees[i]) {
                        continue;
                    } else {
                        latIndex = i - 1;
                        break;
                    }
                }
            } else {
                int len = negLetters.length;
                for (int i = 0; i < len; i++) {
                    if (lat == negDegrees[i]) {
                        latIndex = i;
                        break;
                    }

                    if (lat < negDegrees[i]) {
                        latIndex = i - 1;
                        break;
                    } else {
                        continue;
                    }

                }

            }

            if (latIndex == -1) {
                latIndex = 0;
            }
            if (lat >= 0) {
                if (latIndex == -2) {
                    latIndex = posLetters.length - 1;
                }
                return String.valueOf(posLetters[latIndex]);
            } else {
                if (latIndex == -2) {
                    latIndex = negLetters.length - 1;
                }
                return String.valueOf(negLetters[latIndex]);

            }
        }

    }

}
