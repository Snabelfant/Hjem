package dag.hjem.ruter.model;

public class PlaceById extends Place {
    private int ruterId;

    public PlaceById(String name, int ruterId) {
        super(name);
        this.ruterId = ruterId;
    }

    public String toParam() {
        return Integer.toString(ruterId);
    }
}

