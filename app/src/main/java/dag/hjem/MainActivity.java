package dag.hjem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.gps.GpsObserver;
import dag.hjem.gps.Positioning;
import dag.hjem.gps.UtmPosition;
import dag.hjem.model.TimeDirection;
import dag.hjem.model.TimeOption;
import dag.hjem.model.location.Location;
import dag.hjem.model.location.StopLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;
import dag.hjem.service.TravelServiceCollector;

// 6687 bilder
public class MainActivity extends ActionBarActivity {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Spinner timeDirectionSpinner;
    private Spinner timeOptionSpinner;
    private Button  findButton;

    private Positioning positioning;
private RuterApi ruterApi = new RuterApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.hjem32);
        getSupportActionBar().setTitle(" Hjem");

//        Button gpsPositionButton =  (Button) findViewById(R.id.gpsPosition);
//        GpsObserver gpsObserver = new GpsObserverImpl(gpsPositionButton);
//        positioning = new Positioning(this, gpsObserver);
        initInputs();

        addListeners();
    }

    private void addListeners() {
        findButton.setOnClickListener( new View.OnClickListener() {
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
        findButton = (Button) findViewById(R.id.find);

        List<Location> locations = getLocations();

        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        List<Location> from = new ArrayList<>(locations);
        from.add(0, Location.FROM_HERE);
        from.add(1, Location.HJEM);

        ArrayAdapter<Location> fromAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, from);
        fromAdapter.setDropDownViewResource(R.layout.spinner_layout);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        List<Location> to = new ArrayList<>(locations);
        to.add(0, Location.HJEM);
        to.add(Location.TO_HERE);

        ArrayAdapter<Location> toAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, to);
        toAdapter.setDropDownViewResource(R.layout.spinner_layout);
        toSpinner.setAdapter(toAdapter);

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
    private List<Location> getLocations() {
        List<Location> list = new ArrayList<>();
        list.add(new StopLocation("Bryn kirke", "Bærum", 11111));
        list.add(new StopLocation("Gardermoen", "Jessheim", 23456));
        list.add(new UtmLocation("Øvingshotellet",  512000, 6000001));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainactivitymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mainactivitymenu_add_place) {
            Intent addPlaceActivityIntent = new Intent(this, AddPlaceActivity.class);
            this.startActivity(addPlaceActivityIntent);
            return true;
        }

        if (id == R.id.mainactivitymenu_settings) {
            Toast.makeText(this, "kanskje senere...", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class GpsObserverImpl implements GpsObserver {
        private Button button;

        public GpsObserverImpl(Button button) {
            this.button = button;
        }
        @Override
        public void positionChanged(UtmPosition position) {
            button.setText(position.getUtmEast() + "\n" + position.getUtmNorth() + "\n" + position.getTime());
        }
    }
}
