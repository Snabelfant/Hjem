package dag.hjem.model.location;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dag.hjem.gps.UtmPosition;
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

    public void addUtmPosition(UtmPosition position, String name) throws IOException {
        Location location = new UtmLocation(name, position.getUtmEast(), position.getUtmNorth());
        locationDao.addLocation(location);
    }

    public void addLocation(int x, int y, String name) throws IOException {
        Location location = new UtmLocation(name, x, y);
        locationDao.addLocation(location);
    }

    public List<Location> getAllLocations() throws IOException {
        return new ArrayList<>(locationDao.getLocations());
    }

    public void update(int index, Location location) throws IOException {
        locationDao.update(index, location);
    }

    public void remove(Location location) throws IOException {
        locationDao.remove(location);
    }
}
