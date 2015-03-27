package dag.hjem.ruter.model;

/**
 * Created by Dag on 07.03.2015.
 */
public enum PlaceType {
    POI("POI"),
    Stop("Stopp"),
    Area("Område"),
    Street("Gate/vei");

    private String description;

    private PlaceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
