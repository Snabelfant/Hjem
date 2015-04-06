package dag.hjem.ruter.api;

import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import dag.hjem.rest.Client;
import dag.hjem.ruter.model.House;
import dag.hjem.ruter.model.MonitoredStopVisit;
import dag.hjem.ruter.model.Place;
import dag.hjem.ruter.model.Stop;
import dag.hjem.ruter.model.Street;
import dag.hjem.ruter.model.TravelResponse;

/**
 * Created by Dag on 25.02.2015.
 */
public class RuterApi {
    public RuterApi() {

    }

    public boolean heartBeat() throws IOException {
        String result = new Client("/heartbeat/index").get(String.class);
        Log.i("hjem", result);
        return "Pong".equals(result);
    }

    public Stop getStop(int id) throws IOException {
        return new Client("/place/getstop/").appendPath(Integer.toString(id)).get(Stop.class);
    }

    public List<House> getHouses(int streetId) throws IOException {
        Street street = new Client("/street/getstreet").appendPath(Integer.toString(streetId)).get(Street.class);
        return street.getHouses();
    }

    public List<MonitoredStopVisit> getMonitorStopVisits(int ruterStopId, String lineNo) throws IOException {
        MonitoredStopVisit[] monitoredStopVisits = new Client("stopvisit/getdepartures").appendPath(Integer.toString(ruterStopId))
                .queryParam("linenames", lineNo)
                .get(MonitoredStopVisit[].class);
        return Arrays.asList(monitoredStopVisits);
    }

    public List<Place> getPlaces(String partialPlacename) throws IOException {
        Place[] places = new Client("/place/getplaces")
                .appendPath(partialPlacename)
                .queryParam("counties", "Oslo")
                .queryParam("counties", "Akershus")
                .queryParam("counties", "Ostfold")
                .queryParam("counties", "Vestfold")
                .queryParam("counties", "Buskerud")
                .get(Place[].class);
        return Arrays.asList(places);
    }

    public TravelResponse getTravels(Integer fromPlaceId, Integer fromX, Integer fromY, Integer toPlaceId, Integer toX, Integer toY, boolean isAfter, Calendar departureOrArrivalTime) throws IOException {
        Client c = new Client("/travel/gettravels")
                .queryParam("proposals", 6)
                .queryParam("isAfter", isAfter)
                .queryParam("time", formatDepartureOrArrivalTime(departureOrArrivalTime));

        if (fromPlaceId != null) {
            c = c.queryParam("fromplace", fromPlaceId);
        } else {
            c = c.encodedQuery("fromPlace=" + toXY(fromX, fromY));
        }

        if (toPlaceId != null) {
            c = c.queryParam("toplace", toPlaceId);
        } else {
            c = c.encodedQuery("toPlace=" + toXY(toX, toY));
        }

        return c.get(TravelResponse.class);
    }

    private String toXY(int x, int y) {
        return "(X=" + x + ",Y=" + y + ")";
    }

    private String formatDepartureOrArrivalTime(Calendar departureOrArrivalTime) {
        return new SimpleDateFormat("ddMMyyyyHHmmss").format(departureOrArrivalTime.getTime());
    }
}
