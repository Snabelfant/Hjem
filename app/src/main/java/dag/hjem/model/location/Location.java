package dag.hjem.model.location;

import dag.hjem.model.UtmPosition;

/**
 * Created by Dag on 22.02.2015.
 */
public abstract class Location {
    public static final Location FROM_HERE = new Here("(Herfra)");
    public static final Location TO_HERE = new Here("(Hit)");
    public static final Location HJEM = new UtmLocation("Hjem",new UtmPosition("32", 512000, 6000001) );

    private String name;

    protected Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
