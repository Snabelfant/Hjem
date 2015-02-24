package dag.hjem.model.ruter;

public  abstract class Place {
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String toParam();
}
