package dag.hjem;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }



    private static RuterApi ruterApi;
    private static TravelService travelService;

    public void testGetPlaces() {

    }

    @Test
    public void test() {
        ruterApi = new RuterApi();

        travelService = new TravelService(ruterApi);

        ruterApi.heartBeat();    }
}