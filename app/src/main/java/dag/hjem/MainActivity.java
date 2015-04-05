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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import dag.hjem.model.TimeDirection;
import dag.hjem.model.TimeOption;
import dag.hjem.model.location.Here;
import dag.hjem.model.location.Location;
import dag.hjem.model.location.Locations;
import dag.hjem.model.travelproposal.RealtimeSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.service.Collector;
import dag.hjem.service.TravelService;

public class MainActivity extends ActionBarActivity {
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Spinner timeOptionSpinner;
    private Button findButton;
    private Locations locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.hjem32);
        getSupportActionBar().setTitle(" Hjem");

        findButton = (Button) findViewById(R.id.finddepartures);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        ArrayAdapter<Location> fromAdapter = new ArrayAdapter<>(this,
                R.layout.spinner);
        fromAdapter.setDropDownViewResource(R.layout.spinner);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        ArrayAdapter<Location> toAdapter = new ArrayAdapter<>(this,
                R.layout.spinner);
        toAdapter.setDropDownViewResource(R.layout.spinner);
        toSpinner.setAdapter(toAdapter);

        timeOptionSpinner = (Spinner) findViewById(R.id.timeOptionsSpinner);
        List<TimeOption> timeOptions = TimeOption.getTimes();
        ArrayAdapter<TimeOption> timeOptionAdapter = new ArrayAdapter<>(this,
                R.layout.spinner, timeOptions);
        timeOptionAdapter.setDropDownViewResource(R.layout.spinner);
        timeOptionSpinner.setAdapter(timeOptionAdapter);

        ListView travelList = (ListView) findViewById(R.id.travels);

        final TravelListAdapter travelListAdapter = new TravelListAdapter(this);
        travelList.setAdapter(travelListAdapter);

        locations = new Locations(getApplicationContext());

        updateLocationSpinners();

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Location fromLocation = (Location) fromSpinner.getSelectedItem();
                    Location toLocation = (Location) toSpinner.getSelectedItem();
                    TimeOption timeOption = (TimeOption) timeOptionSpinner.getSelectedItem();
                    boolean isAfter = TimeDirection.AFTER.equals(timeOption.getTimeDirection());

                    TravelService travelService = new TravelService();
                    travelService.getTravelProposals(fromLocation, toLocation, isAfter, timeOption.getTime(), new TravelSearchCollector(travelListAdapter));
                } catch (IOException e) {
                    YesNoCancel.show(getApplicationContext(), "Oi!", e.toString(), YesNoCancel.EMPTY, null, null);
                } catch (RuntimeException rte) {
                    YesNoCancel.show(MainActivity.this, "Hmm", rte.getMessage(), YesNoCancel.EMPTY, null, null);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateLocationSpinners();
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
            addPlaceActivityIntent.putExtra("gpsposition", Here.getCurrentPosition());
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

    private class TravelSearchCollector extends Collector {
        private TravelListAdapter travelListAdapter;

        private TravelSearchCollector(TravelListAdapter travelListAdapter) {
            this.travelListAdapter = travelListAdapter;
        }

        @Override
        public void setTravelSearchResult(TravelSearchResult result) {
            Log.i("hjem", "TR=" + result.toString());

            if (result.getException() != null) {
                YesNoCancel.show(MainActivity.this, "Oi!", result.getException().toString(), YesNoCancel.EMPTY, null, null);
            } else {
                travelListAdapter.setTravelList(result.getTravels());

                if (result.getReisError() != null) {
                    YesNoCancel.show(MainActivity.this, "!", result.getReisError(), YesNoCancel.EMPTY, null, null);
                } else {
                    if (result.getTravels().size() == 0) {
                        YesNoCancel.show(MainActivity.this, "!", "Ingen forslag", YesNoCancel.EMPTY, null, null);
                    }
                }
            }
        }

        @Override
        public void setRealtimeSearchResult(RealtimeSearchResult result) {
            if (result.getException() != null) {
                YesNoCancel.show(MainActivity.this, "Oi!", result.getException().toString(), YesNoCancel.EMPTY, null, null);
            } else {
                Log.i("hjem", result.toString());
            }
        }
    }

}
