package dag.hjem;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.Test;

import java.io.IOException;

import dag.hjem.model.travelproposal.RealtimeSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.Collector;
import dag.hjem.service.TravelService;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private RuterApi ruterApi;

    public ApplicationTest() {
        super(Application.class);
    }


    @Test
    public void test() throws IOException {
//        ruterApi = new RuterApi();
//
//        ruterApi.getMonitorStopVisits(3010066, "151");

        Collector collector = new Collector() {
            @Override
            public void setRealtimeSearchResult(RealtimeSearchResult realtimeSearchResult) {
                Log.i("hjem", realtimeSearchResult.toString());
            }
        };
        TravelService travelService = new TravelService();
        travelService.getRealtimeCalls(3010066, "151", "Oslo bussterminal" +
                "", collector);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}