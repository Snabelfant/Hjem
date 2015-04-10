package dag.hjem.model.location;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dag on 12.03.2015.
 */
interface LocationDao extends Serializable {
    List<Location> getLocations() throws IOException;

    void addLocation(Location location) throws IOException;

    void update(int index, Location location) throws IOException;

    void remove(Location location) throws IOException;
}
