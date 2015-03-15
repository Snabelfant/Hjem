package dag.hjem.model.travelproposal;

import java.io.Serializable;

/**
 * Created by Dag on 08.03.2015.
 */
public class Place implements Serializable {
    private int ruterId;
    private String name;
    private String district;
    private String type;

    public Place(int ruterId, String name, String district, String placeTypeDescription) {
        this.ruterId = ruterId;
        this.name = name;
        this.district = district;
        this.type = placeTypeDescription;
    }

    public static Place fromRuter(dag.hjem.ruter.model.Place ruterPlace) {
        String placeTypeDescription = ruterPlace.getPlaceType() == null ? null : ruterPlace.getPlaceType().getDescription();
        return new Place(ruterPlace.getId(), ruterPlace.getName(), ruterPlace.getDistrict(), placeTypeDescription);
    }

    public int getRuterId() {
        return ruterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Place{" +
                "ruterId=" + ruterId +
                ", name='" + name + '\'' +
                ", district='" + district + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
