package dag.hjem.model.location;

import android.util.Log;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Dag on 09.03.2015.
 */
public class LocationDAO {
    private ObjectMapper mapper;
    private StoredLocations storedLocations = null;

    public LocationDAO() {
        mapper = new ObjectMapper();
    }

    public List<Location> getDepartureLocations() throws IOException {
        loadLocations();
        return storedLocations.getLocations();
    }

    public void addLocation(Location location) throws IOException {
        saveLocations();
    }

    private void loadLocations() throws IOException {
        if (storedLocations == null) {
            String json =
                    "   {  \"Locations\": " +
                            "      [ {\"Type\":\"stop\",\"Name\":\"Jobb\", \"RuterId\":1000027557}," +
                            "        {\"Type\":\"utm\",\"Name\":\"Kristian\", \"x\":597894, \"y\":6647727}" +
                            "      ] " +
                            "   }";

            Log.i("hjem", json);
            storedLocations = mapper.readValue(json, StoredLocations.class);
            Log.i("hjem", storedLocations.locations.get(0).toString());
            Log.i("hjem", storedLocations.locations.get(1).toString());
        } else {
            Log.i("hjem", "ikke null");
        }
    }

    private void saveLocations() throws IOException {
        if (storedLocations != null) {
            StringWriter stringWriter = new StringWriter();
            mapper.writerWithDefaultPrettyPrinter().writeValue(stringWriter, storedLocations);
            Log.i("hjem", "save=" + stringWriter.toString());
        }

    }

    private static class StoredLocations {
        private List<Location> locations;

        private StoredLocations(@JsonProperty("Locations") List<Location> locations) {
            this.locations = locations;
        }

        @JsonProperty("Locations")
        public List<Location> getLocations() {
            return locations;
        }

    }

}
