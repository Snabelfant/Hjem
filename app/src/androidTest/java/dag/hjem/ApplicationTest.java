package dag.hjem;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.LocationDaoImpl;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;
import dag.hjem.service.TravelServiceCollector;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private RuterApi ruterApi;

    private TravelService travelService;

    public ApplicationTest() {
        super(Application.class);
    }


    @Test
    public void test() throws IOException {
        Log.i("hjem", "DAO");
        LocationDaoImpl dao = new LocationDaoImpl();

        dao.getDepartureLocations();
        dao.addLocation(null);
    }

    @Test
    public void test2() throws IOException {
        TravelServiceCollector travelServiceCollector = new TravelServiceCollector() {
            @Override
            public void setTravelSearchResult(TravelSearchResult result) {
                Log.i("hjem", "TSR! " + result.toString());
            }

            @Override
            public void setPlaceSearchResult(PlaceSearchResult result) {
                Log.i("hjem", "PSR! " + result.toString());
            }
        };

        ruterApi = new RuterApi();
        travelService = new TravelService(ruterApi, travelServiceCollector);

//        boolean b = ruterApi.heartBeat();
//
//        Stop stop = ruterApi.getStop(2190795);
//        Log.i("hjem", stop.toString());
//
//        TravelResponse travelResponse = ruterApi.getTravels(2190780,null,null,1000021097,null,null, true, Calendar.getInstance());
//        Log.i("hjem", travelResponse.toString());
//        travelResponse = ruterApi.getTravels(2190780,null,null, 1000027623, null, null, true, Calendar.getInstance());
//        Log.i("hjem", travelResponse.toString());
//        travelResponse = ruterApi.getTravels(2190640,null, null,1000027623, null, null, true, Calendar.getInstance());
//        Log.i("hjem", travelResponse.toString());
//
//        List<Place> places = ruterApi.getPlaces("bryn k");
//        Log.i("hjem", "P=" + places.toString());
//
//        places = ruterApi.getPlaces("tåsen");
//        Log.i("hjem", places.size() + "Q=" + places.toString());

//        Location from = new StopLocation("q", "Q", 2190780);
//        Location to = new StopLocation("r", "R", 1000027623);
//        travelService.getTravelProposals(from, to, true, Calendar.getInstance());
//        Log.i("hjem", "TR1");

        Location to = new UtmLocation("kr", 597894, 6647727);
        travelService.getTravelProposals(Location.HJEM, to, true, Calendar.getInstance());

        Log.i("hjem", "TR2");

        travelService.getPlaces("Bel");

        LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
        List<Location> locations = locationDaoImpl.getDepartureLocations();
        travelService.getTravelProposals(locations.get(0), locations.get(1), true, Calendar.getInstance());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("hjem", "Ferdig");
    }

}