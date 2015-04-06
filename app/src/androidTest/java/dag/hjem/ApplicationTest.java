package dag.hjem;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import dag.hjem.ruter.api.RuterApi;
import dag.hjem.ruter.model.MonitoredStopVisit;

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
        ruterApi = new RuterApi();

        List<MonitoredStopVisit> monitoredStopVisits = ruterApi.getMonitorStopVisits(3010066, "151");

    }

}