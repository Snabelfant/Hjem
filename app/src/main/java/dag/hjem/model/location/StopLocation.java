package dag.hjem.model.location;

/**
 * Created by Dag on 22.02.2015.
 */
public class StopLocation extends Location {
    private int ruterId;
    private String district;

    public StopLocation(String name, String district, int ruterId) {
        super(name);
        this.ruterId = ruterId;
        this.district = district;
    }

    public int getRuterId() {
        return ruterId;
    }

    public String getDistrict() {
        return district;
    }
}
