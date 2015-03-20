package dag.hjem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dag.hjem.gps.UtmPosition;
import dag.hjem.model.location.Locations;
import dag.hjem.model.travelproposal.Place;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;

public class AddPlaceActivity extends Activity {
    private TravelService travelService;
    private UtmPosition lastKnownGpsPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplaceactivity);

        Bundle bundle = getIntent().getExtras();
        lastKnownGpsPosition = (UtmPosition) bundle.get("gpsposition");
        Log.i("hjem", "gps=" + lastKnownGpsPosition);

        ListView placesFound = (ListView) findViewById(R.id.placesfound);
        final PlaceListAdapter placeListAdapter = new PlaceListAdapter(this);
        placesFound.setAdapter(placeListAdapter);

        Button saveGpsPosition = (Button) findViewById(R.id.addplace_gps);
        saveGpsPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddPlaceActivity.this, "Lagre gps...", Toast.LENGTH_LONG).show();
                Bundle b = new Bundle();
                b.putSerializable("utmposition", lastKnownGpsPosition);
                AddPlaceDialogFragment addPlaceDialogFragment = new AddPlaceDialogFragment();
                addPlaceDialogFragment.setArguments(b);
                addPlaceDialogFragment.show(getFragmentManager(), "Nytt gps-sted");
            }
        });

        saveGpsPosition.setEnabled(lastKnownGpsPosition != null);
        travelService = new TravelService(new RuterApi(), placeListAdapter);

        EditText searchTermView = (EditText) findViewById(R.id.placesearchterm);
        searchTermView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        searchTermView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchTerm = textView.getText().toString().trim();

                    if (!"".equals(searchTerm)) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        try {
                            placeListAdapter.clear();
                            travelService.getPlaces(searchTerm);
                        } catch (IOException e) {
                            Toast.makeText(AddPlaceActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    return true;
                }

                return false;
            }
        });


        placesFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place selectedPlace = (Place) parent.getAdapter().getItem(position);
                Bundle b = new Bundle();
                b.putSerializable("place", selectedPlace);
                AddPlaceDialogFragment addPlaceDialogFragment = new AddPlaceDialogFragment();
                addPlaceDialogFragment.setArguments(b);
                addPlaceDialogFragment.show(getFragmentManager(), "Nytt Ruter-sted");
            }
        });
    }

    public static class AddPlaceDialogFragment extends DialogFragment {
        public AddPlaceDialogFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle bundle = getArguments();

            final Place newPlace = (Place) bundle.get("place");
            final UtmPosition newUtmPosition = (UtmPosition) bundle.get("utmposition");

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View addPlaceView = inflater.inflate(R.layout.addplacedialog, null);
            builder.setView(addPlaceView);

            final TextView name = (TextView) addPlaceView.findViewById(R.id.addplace_newname);

            if (newPlace != null) {
                name.setText(newPlace.getName());
            } else {
                name.setText(newUtmPosition.getUtmNorth() + "/" + newUtmPosition.getUtmEast());
            }

            name.setSelected(true);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    try {
                        Locations locations = new Locations(getActivity());

                        if (newPlace != null) {
                            newPlace.setName(name.getText().toString());
                            locations.addPlace(newPlace);
                        } else {
                            locations.addUtmPosition(newUtmPosition, name.getText().toString());
                        }
                    } catch (Exception e) {
                        Log.e("hjem", "Feil: " + e.toString());
                        YesNoCancel.show(getActivity(), "Oi!!", e.getMessage(), YesNoCancel.EMPTY, null, null);
                        return;
                    }
                }
            })
                    .setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            return builder.create();
        }
    }
}

