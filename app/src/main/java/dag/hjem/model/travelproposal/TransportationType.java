package dag.hjem.model.travelproposal;

public enum TransportationType {
    WALKING("Gå"),
    AIRPORTBUS("FB"),
    BUS("Buss"),
    AIRPORTTRAIN("FT"),
    BOAT("Båt"),
    TRAIN("Tog"),
    TRAM("Trikk"),
    METRO("(T)");

    private String description;

    TransportationType(String description) {
        this.description = description;
    }

    public static TransportationType fromRuter(dag.hjem.ruter.model.TransportationType ruterType) {
        return valueOf(ruterType.name());
    }

    public String getDescription() {
        return description;
    }

}
