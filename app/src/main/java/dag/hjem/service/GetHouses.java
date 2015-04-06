package dag.hjem.service;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import dag.hjem.model.travelproposal.HouseSearchResult;
import dag.hjem.ruter.model.House;

/**
 * Created by Dag on 29.03.2015.
 */
class GetHouses extends Service {


    public GetHouses(Collector collector) {
        super(collector, 1);
    }

    void getHouses(final int ruterStreetId) throws IOException {
        AsyncTask<Integer, Integer, HouseSearchResult> task = new AsyncTask<Integer, Integer, HouseSearchResult>() {

            @Override
            protected HouseSearchResult doInBackground(Integer... params) {
                int ruterStreetId = params[0];
                try {
                    List<House> ruterHouseSearchResult = ruterApi.getHouses(ruterStreetId);
                    return HouseSearchResult.fromRuter(ruterHouseSearchResult);
                } catch (IOException e) {
                    return HouseSearchResult.fromException(e);
                }
            }

            @Override
            protected void onPostExecute(HouseSearchResult result) {
                collector.setHouseSearchResult(result);
            }
        };

        task.executeOnExecutor(executorService, ruterStreetId);
    }

}
