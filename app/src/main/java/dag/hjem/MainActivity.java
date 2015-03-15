package dag.hjem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import dag.hjem.gps.GpsObserver;
import dag.hjem.gps.Positioning;
import dag.hjem.gps.UtmPosition;
import dag.hjem.model.TimeDirection;
import dag.hjem.model.TimeOption;
import dag.hjem.model.location.Location;
import dag.hjem.model.location.Locations;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;
import dag.hjem.service.TravelServiceCollector;

public class MainActivity extends ActionBarActivity {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Spinner timeDirectionSpinner;
    private Spinner timeOptionSpinner;
    private Button findButton;

    private Positioning positioning;
    private RuterApi ruterApi;
    private UtmPosition lastKnownGpsPosition;
    private Locations locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.hjem32);
        getSupportActionBar().setTitle(" Hjem");

        lastKnownGpsPosition = null;
        GpsObserver gpsObserver = new GpsObserverImpl();
        positioning = new Positioning(this, gpsObserver);

        ruterApi = new RuterApi();

        findButton = (Button) findViewById(R.id.finddepartures);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        ArrayAdapter<Location> fromAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout);
        fromAdapter.setDropDownViewResource(R.layout.spinner_layout);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        ArrayAdapter<Location> toAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout);
        toAdapter.setDropDownViewResource(R.layout.spinner_layout);
        toSpinner.setAdapter(toAdapter);

        locations = new Locations(getApplicationContext());

        updateLocationSpinners();
        initInputs();

        addListeners();
    }

    private void addListeners() {
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelService travelService = new TravelService(new RuterApi(), new TravelServiceCollector() {
                    @Override
                    public void setTravelSearchResult(TravelSearchResult result) {

                    }

                    @Override
                    public void setPlaceSearchResult(PlaceSearchResult result) {

                    }
                });
                Toast.makeText(MainActivity.this, "Finn tider...", Toast.LENGTH_LONG);
            }
        });
//        timeDirectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                TimeDirection timeDirection = (TimeDirection) parentView.getItemAtPosition(position);
//                List<TimeOption> timeOptions = getTimeSpinnerList(timeDirection);
//                ArrayAdapter<TimeOption> timeOptionAdapter = new ArrayAdapter<TimeOption>(this,
//                        R.layout.spinner_layout, timeOptions);
//                timeOptionSpinner.setAdapter(timeOptionAdapter);
//                }
//            };
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
    }

    private void initInputs() {
        timeDirectionSpinner = (Spinner) findViewById(R.id.timeDirectionSpinner);
        ArrayAdapter<TimeDirection> timeDirectionAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, TimeDirection.asList());
        timeDirectionAdapter.setDropDownViewResource(R.layout.spinner_layout);
        timeDirectionSpinner.setAdapter(timeDirectionAdapter);
        timeDirectionSpinner.setSelection(0);

        timeOptionSpinner = (Spinner) findViewById(R.id.timeOptionsSpinner);
        List<TimeOption> timeOptions = getTimeSpinnerList((TimeDirection) timeDirectionSpinner.getSelectedItem());
        ArrayAdapter<TimeOption> timeOptionAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, timeOptions);
        timeOptionAdapter.setDropDownViewResource(R.layout.spinner_layout);
        timeOptionSpinner.setAdapter(timeOptionAdapter);
    }

    private List<TimeOption> getTimeSpinnerList(TimeDirection timeDirection) {
        if (timeDirection == TimeDirection.AFTER) {
            return TimeOption.getTimesAfter();
        } else {
            return TimeOption.getTimesBefore();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateLocationSpinners();
        Log.i("hjem", "Tilbake=" + data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivitymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mainactivitymenu_add_place) {
            Intent addPlaceActivityIntent = new Intent(this, AddPlaceActivity.class);
            this.startActivityForResult(addPlaceActivityIntent, 12345);
            return true;
        }

        if (id == R.id.mainactivitymenu_settings) {
            Toast.makeText(this, "kanskje senere...", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateLocationSpinners() {
        try {
            updateLocationSpinner(fromSpinner, locations.getDepartureLocations());
            updateLocationSpinner(toSpinner, locations.getArrivalLocations());
        } catch (IOException e) {
            YesNoCancel.show(this, "Oi!", e.toString(), YesNoCancel.EMPTY, null, null);
        }
    }

    private void updateLocationSpinner(Spinner spinner, List<Location> locations) {
        ArrayAdapter<Location> adapter = ((ArrayAdapter<Location>) spinner.getAdapter());
        adapter.clear();
        adapter.addAll(locations);
    }

    private class GpsObserverImpl implements GpsObserver {

        @Override
        public void positionChanged(UtmPosition position) {
            lastKnownGpsPosition = position;
            Log.i("hjem", position.getUtmEast() + " " + position.getUtmNorth() + " " + position.getTime());
        }
    }
}
