package dag.hjem.ruter.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by Dag on 27.03.2015.
 */
public class Street {
    private int id;
    private String district;
    private String name;
    private List<House> houses;
    private PlaceType placeType;

    public Street(@JsonProperty("ID") int id,
                  @JsonProperty("PlaceType") PlaceType placeType,
                  @JsonProperty("Name") String name,
                  @JsonProperty("District") String district,
                  @JsonProperty("Houses") List<House> houses
    ) {
        this.id = id;
        this.district = district;
        this.name = name;
        this.houses = houses;
        this.placeType = placeType;
    }
}
