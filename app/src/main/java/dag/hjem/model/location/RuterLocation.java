package dag.hjem.model.location;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Dag on 22.02.2015.
 */
public class RuterLocation extends Location {
    private int ruterId;
    private String district;

    public RuterLocation(@JsonProperty("Navn") String name, @JsonProperty("Område") String district, @JsonProperty("RuterId") int ruterId) {
        super(name);
        this.ruterId = ruterId;
        this.district = district;
    }

    public String xtoString() {
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
