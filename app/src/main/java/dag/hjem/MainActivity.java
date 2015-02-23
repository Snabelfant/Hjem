package dag.hjem;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dag.hjem.model.UtmPosition;
import dag.hjem.model.location.Location;
import dag.hjem.model.location.StopLocation;
import dag.hjem.model.location.UtmLocation;
import dag.hjem.model.ruter.Stop;


public class MainActivity extends ActionBarActivity {
    private Spinner fromSpinner;
    private Spinner toSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.hjem32);
        getSupportActionBar().setTitle(" Hjem");

        initLists();
    }

    private void initLists() {
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
        to.add( Location.TO_HERE);

        ArrayAdapter<Location> toAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_layout, to);
        toAdapter.setDropDownViewResource(R.layout.spinner_layout);
        toSpinner.setAdapter(toAdapter);
    }

    private List<Location> getLocations() {
        List<Location> list = new ArrayList<>();
        list.add(new StopLocation("Bryn kirke", new Stop()));
        list.add(new StopLocation("Gardermoen", new Stop()));
        list.add(new StopLocation("Jobb", new Stop()));
        list.add(new UtmLocation("Øvingshotellet", new UtmPosition("32", 512000, 6000001)));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
