package dag.hjem.model.location;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dag.hjem.model.travelproposal.Place;

public class Locations {
    private LocationDao locationDao;

    public Locations(Context context) {
        locationDao = new LocationDaoImpl(context);
    }

    public List<Location> getDepartureLocations() throws IOException {
        List<Location> departureLocations = new ArrayList<>(locationDao.getLocations());
        departureLocations.add(0, Location.FROM_HERE);
        departureLocations.add(1, Location.HJEM);
        return departureLocations;
    }

    public List<Location> getArrivalLocations() throws IOException {
        List<Location> arrivalLocations = new ArrayList<>(locationDao.getLocations());
        arrivalLocations.add(0, Location.HJEM);
        arrivalLocations.add(Location.TO_HERE);
        return arrivalLocations;
    }

    public void addPlace(Place place) throws IOException {
        Location location = new RuterLocation(place.getName(), place.getDistrict(), place.getRuterId());
        locationDao.addLocation(location);
    }
}
