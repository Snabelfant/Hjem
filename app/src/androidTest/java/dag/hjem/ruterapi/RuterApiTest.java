package dag.hjem.ruterapi;

import org.junit.Before;
import org.junit.Test;

public class RuterApiTest {

    private RuterApi ruterApi;

    @Before
    public void init() {
        ruterApi = new RuterApi();
    }

    @Test
    private void testHeartBeat() {
        ruterApi.heartBeat();
    }

    @Test
    public void testGetPlaces() {

    }
}