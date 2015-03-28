package dag.hjem.model.travelproposal;

public class House {
    private int x;
    private int y;
    private String name;

    public House(int x,
                 int y,
                 String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public static House fromRuter(dag.hjem.ruter.model.House ruterHouse) {
        return new House(ruterHouse.getX(), ruterHouse.getY(), ruterHouse.getName());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
