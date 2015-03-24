package dag.hjem.model.travelproposal;

import dag.hjem.R;

public enum TransportationType {
    WALKING("Gå", R.drawable.ga),
    AIRPORTBUS("FB", R.drawable.buss),
    BUS("Buss", R.drawable.buss),
    AIRPORTTRAIN("FT", R.drawable.tog),
    BOAT("Båt", R.drawable.bat),
    TRAIN("Tog", R.drawable.tog),
    TRAM("Trikk", R.drawable.trikk),
    METRO("(T)", R.drawable.tbane);

    private String description;
    private int iconId;

    TransportationType(String description, int iconId) {
        this.description = description;
        this.iconId = iconId;
    }

    public static TransportationType fromRuter(dag.hjem.ruter.model.TransportationType ruterType) {
        return valueOf(ruterType.name());
    }

    public int getIconId() {
        return iconId;
    }

    public String getDescription() {
        return description;
    }

}
