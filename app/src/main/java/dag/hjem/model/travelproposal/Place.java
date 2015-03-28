package dag.hjem.model.travelproposal;

import java.io.Serializable;


/**
 * Created by Dag on 08.03.2015.
 */
public class Place implements Serializable {
    private int ruterId;
    private String name;
    private String district;
    private PlaceType type;
    private boolean isRealtimeStop;
    private House house;

    public Place(int ruterId, String name, String district, PlaceType placeType, boolean realTimeStop) {
        this.ruterId = ruterId;
        this.name = name;
        this.district = district;
        this.type = placeType;
        this.house = null;
        this.isRealtimeStop = isRealtimeStop;
    }

    public static Place fromRuter(dag.hjem.ruter.model.Place ruterPlace) {
        return new Place(ruterPlace.getId(), ruterPlace.getName(), ruterPlace.getDistrict(), PlaceType.fromRuter(ruterPlace.getPlaceType()), ruterPlace.isRealTimeStop());
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void checkCompleteness() {
        if (PlaceType.STREET == type) {
            if (house == null) {
                throw new RuntimeException("Husnummer må velges");
            }
        }
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

    public PlaceType getType() {
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
