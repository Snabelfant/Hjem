package dag.hjem.service;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.ruter.model.Place;

/**
 * Created by Dag on 29.03.2015.
 */
class GetPlaces extends Service {
    GetPlaces(Collector collector) {
        super(collector, 1);
    }

    void getPlaces(String partialName) throws IOException {
        AsyncTask<String, Integer, PlaceSearchResult> task = new AsyncTask<String, Integer, PlaceSearchResult>() {

            @Override
            protected PlaceSearchResult doInBackground(String... params) {
                String partialName = params[0];
                try {
                    List<Place> ruterPlaceSearchResult = ruterApi.getPlaces(partialName);
                    return PlaceSearchResult.fromRuter(ruterPlaceSearchResult);
                } catch (IOException e) {
                    return PlaceSearchResult.fromException(e);
                }
            }

            @Override
            protected void onPostExecute(PlaceSearchResult result) {
                collector.setPlaceSearchResult(result);
            }
        };

        task.executeOnExecutor(executorService, partialName);
    }


}
