package dag.hjem.model.location;

import dag.hjem.model.UtmPosition;

/**
 * Created by Dag on 22.02.2015.
 */
public class UtmLocation extends Location {
    private UtmPosition position;

    public UtmLocation(String name, UtmPosition position) {
        super(name);
        this.position = position;
    }


}
