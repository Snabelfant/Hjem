package dag.hjem;

import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;

public class RuterApiTest {



    private static RuterApi ruterApi;
    private static TravelService travelService;

    private static void testHeartBeat() {
        ruterApi.heartBeat();
    }

    public void testGetPlaces() {

    }

    public static void main(String[] args) {
        ruterApi = new RuterApi();

        travelService = new TravelService(ruterApi);

        testHeartBeat();
    }
}