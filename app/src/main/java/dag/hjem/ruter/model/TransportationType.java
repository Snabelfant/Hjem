package dag.hjem.ruter.model;

public enum TransportationType {
    WALKING("Gå"),
    AIRPORTBUS("FB"),
    BUS("Buss"),
    DUMMY("?"),
    AIRPORTTRAIN("FT"),
    BOAT("Båt"),
    TRAIN("Tog"),
    TRAM("Trikk"),
    METRO("(T)");

    private String description;

    TransportationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
