package dag.hjem.model.location;

import dag.hjem.ruter.model.Stop;

/**
 * Created by Dag on 22.02.2015.
 */
public class StopLocation extends Location {
    private Stop stop;

    public StopLocation(String name, Stop stop) {
        super(name);
        this.stop = stop;
    }
}
