package dag.hjem.model.travelproposal;

/**
 * Created by Dag on 07.03.2015.
 */
public enum PlaceType {
    POI("POI"),
    STOP("Stopp"),
    AREA("Område"),
    STREET("Gate/vei");

    private String description;

    private PlaceType(String description) {
        this.description = description;
    }

    public static PlaceType fromRuter(dag.hjem.ruter.model.PlaceType ruterType) {
        for (PlaceType placeType : PlaceType.values()) {
            if (placeType.name().equalsIgnoreCase(ruterType.name())) {
                return placeType;
            }
        }

        throw new IllegalArgumentException("Ingen type for " + ruterType);
    }

    public String getDescription() {
        return description;
    }

}
