package dag.hjem.model.location;

import android.content.Context;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dag.hjem.Util;

/**
 * Created by Dag on 09.03.2015.
 */
public class LocationDaoImpl implements LocationDao {
    private final Context context;
    private LocationComparator locationComparator = new LocationComparator();


    public LocationDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Location> getLocations() throws IOException {
        return loadLocations();
    }

    @Override
    public void addLocation(Location location) throws IOException {
        List<Location> locations = loadLocations();
        locations.add(location);
        saveLocations(locations);
        Util.log("La til " + location);
    }

    private List<Location> loadLocations() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Location[] locations;
        File file = Util.getExternalFile(context, "Steder.json");

        if (file.exists()) {
            locations = mapper.readValue(file, Location[].class);
        } else {
            locations = new Location[0];
        }

        List<Location> locationsAsList = new ArrayList<>(Arrays.asList(locations));
        Util.log(file.exists() + " " + file.getAbsolutePath() + " loadlocations=" + locationsAsList.toString());
        return locationsAsList;
    }

    private void saveLocations(List<Location> locations) throws IOException {
        Collections.sort(locations, locationComparator);
        Location[] locationsAsArray = locations.toArray(new Location[0]);
        ObjectMapper mapper = new ObjectMapper();
        File file = Util.getExternalFile(context, "Steder.json");

        mapper.writerWithDefaultPrettyPrinter().writeValue(file, locationsAsArray);
        Util.log(file.getAbsolutePath() + " save=" + locations.toString());
    }

    @Override
    public void update(int index, Location location) throws IOException {
        List<Location> locations = loadLocations();
        locations.set(index, location);
        saveLocations(locations);
        Util.log("Endret " + index + "; " + location);
    }

    @Override
    public void remove(Location location) throws IOException {
        List<Location> locations = loadLocations();
        Util.log("Finnes " + location + ": " + locations.contains(location));
        locations.remove(location);
        saveLocations(locations);
        Util.log("Slettet " + location);

    }

    private class LocationComparator implements Comparator<Location> {
        @Override
        public int compare(Location lhs, Location rhs) {
            return lhs.getName().compareToIgnoreCase(rhs.getName());
        }
    }
}
