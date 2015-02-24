package dag.hjem.ruterapi;

import java.util.Date;

import dag.hjem.model.ruter.Place;
import dag.hjem.model.ruter.Stop;
import dag.hjem.model.ruter.TravelResponse;

/**
 * Created by Dag on 25.02.2015.
 */
public class RuterApi {
    private Client client;

    client = ClientBuilder.newClient().register(JacksonFeature.class);
    ruterApi = client.target("http://reisapi.ruter.no");
}

    private boolean heartBeat() {
        WebTarget target = ruterApi.path("/heartbeat/index");
        Response response = call(target);
        checkResponse(response);
        return "\"Pong\"".equals(response.readEntity(String.class));
    }

    private Stop getStop(int id) {
        WebTarget target = ruterApi.path("/Place/GetStop/{id}").resolveTemplate("id", id);
        Response response = call(target);
        checkResponse(response);
        Stop stop = response.readEntity(Stop.class);

        if (stop.getId() != ) {
            return stop;
        }

        throw new RuntimeException("Stop.id==0: " + id);
    }

    private TravelResponse getTravel(Place fromPlace, Place toPlace, boolean isAfter, Date departureOrArrivalTime) {
        WebTarget target = ruterApi.path("travel/gettravels")
                .queryParam("fromplace", fromPlace.toParam())
                .queryParam("toplace", toPlace.toParam())
                .queryParam("isafter", isAfter)
//                .queryParam("time", new SimpleDateFormat("ddMMYYYYhhmmss").format(departureOrArrivalTime))
                ;
//                = & changemargin = {changemargin} & changepunish = {changepunish} & walkingfactor = {walkingfactor} & proposals = {proposals} & transporttypes = {transporttypes} & maxwalkingminutes = {maxwalkingminutes} & linenames = {linenames};
        Response response = call(target);

        TravelResponse travelResponse = response.readEntity(TravelResponse.class);

        if (travelResponse.getReisError() != null) {
            throw new RuntimeException(travelResponse.getReisError().getDescription());
        }
        return travelResponse;
    }

    private Response call(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
    }

    private void checkResponse(Response response) {
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.toString());
        }
    }

    private TravelSearchResult getTravelProposals(Place fromPlace, Place toPlace, boolean isAfter, Date departureOrArrivalTime) {
        TravelResponse travelResponse = getTravel(fromPlace, toPlace, isAfter, departureOrArrivalTime);
        return TravelSearchResult.fromRuter(fromPlace.getName(), toPlace.getName(), travelResponse);
    }

}
