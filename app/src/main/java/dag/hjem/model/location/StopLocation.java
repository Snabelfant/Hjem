package dag.hjem.model.location;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Dag on 22.02.2015.
 */
public class StopLocation extends Location {
    private int ruterId;
    private String district;

    public StopLocation(@JsonProperty("Name") String name, @JsonProperty("District") String district, @JsonProperty("RuterId") int ruterId) {
        super(name);
        this.ruterId = ruterId;
        this.district = district;
    }

    @Override
    public String toString() {
        return "StopLocation{" +
                "name=" + super.getName() +
                ", ruterId=" + ruterId +
                ", district='" + district + '\'' +
                '}';
    }

    public int getRuterId() {
        return ruterId;
    }

    public String getDistrict() {
        return district;
    }
}
