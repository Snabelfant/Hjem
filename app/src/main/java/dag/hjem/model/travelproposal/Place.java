package dag.hjem.model.travelproposal;

/**
 * Created by Dag on 08.03.2015.
 */
public class Place {
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
