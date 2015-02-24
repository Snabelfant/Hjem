package dag.hjem.model.ruter;
public  class PlaceByLocation extends Place {
    private Location location;

    public PlaceByLocation(String name, Location location) {
        super(name);
        this.location = location;
    }

    @Override
    public String toParam() {
        return "(X=" + location.getX() + ",Y=" + location.getY() + ")";
    }
}
