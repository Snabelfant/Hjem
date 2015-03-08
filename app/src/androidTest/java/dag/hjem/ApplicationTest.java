package dag.hjem;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.StopLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.ruter.model.Place;
import dag.hjem.ruter.model.Stop;
import dag.hjem.ruter.model.TravelResponse;
import dag.hjem.service.TravelService;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private RuterApi ruterApi;

    private TravelService travelService;

    public ApplicationTest() {
        super(Application.class);
    }

    @Before
    public void init() {
        ruterApi = new RuterApi();
        travelService = new TravelService(ruterApi);
    }

    public void testGetPlaces() {
    }

    @Test
    public void test() throws IOException {
        ruterApi = new RuterApi();
        travelService = new TravelService(ruterApi);

        boolean b = ruterApi.heartBeat();

        Stop stop = ruterApi.getStop(2190795);
        Log.i("hjem", stop.toString());

        TravelResponse travelResponse = ruterApi.getTravels(2190780,null,null,1000021097,null,null, true, Calendar.getInstance());
        Log.i("hjem", travelResponse.toString());
        travelResponse = ruterApi.getTravels(2190780,null,null, 1000027623, null, null, true, Calendar.getInstance());
        Log.i("hjem", travelResponse.toString());
        travelResponse = ruterApi.getTravels(2190640,null, null,1000027623, null, null, true, Calendar.getInstance());
        Log.i("hjem", travelResponse.toString());

        List<Place> places = ruterApi.getPlaces("bryn k");
        Log.i("hjem", "P=" + places.toString());

        places = ruterApi.getPlaces("tåsen");
        Log.i("hjem", places.size() + "Q=" + places.toString());

        Location from = new StopLocation("q","Q",2190780);
        Location to = new StopLocation("r","R",1000027623);
        TravelSearchResult result = travelService.getTravelProposals(from, to, true, Calendar.getInstance());
        Log.i("hjem", "TR=" + result.toString());
// http://reisapi.ruter.no/travel/gettravels?isAfter=true&time=08032015112722&fromPlace=(X=583481,Y=6643824)&toPlace=(X=597894,Y=6647727)
        to = new UtmLocation("kr",597894,6647727);
        result = travelService.getTravelProposals(Location.HJEM, to, true, Calendar.getInstance());

        Log.i("hjem", "TR=" + result.toString());

    }

}