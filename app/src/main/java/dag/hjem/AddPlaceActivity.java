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

import java.io.IOException;

import dag.hjem.gps.UtmPosition;
import dag.hjem.model.location.Locations;
import dag.hjem.model.travelproposal.House;
import dag.hjem.model.travelproposal.Place;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.service.Collector;
import dag.hjem.service.TravelService;

public class AddPlaceActivity extends Activity {
    private UtmPosition lastKnownGpsPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplaceactivity);

        Bundle bundle = getIntent().getExtras();
        lastKnownGpsPosition = (UtmPosition) bundle.get("gpsposition");

        ListView placesFound = (ListView) findViewById(R.id.placesfound);
        final PlaceListAdapter placeListAdapter = new PlaceListAdapter(this);
        placesFound.setAdapter(placeListAdapter);

        Button saveGpsPosition = (Button) findViewById(R.id.addplace_gps);
        saveGpsPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("utmposition", lastKnownGpsPosition);
                AddPlaceDialogFragment addPlaceDialogFragment = new AddPlaceDialogFragment();
                addPlaceDialogFragment.setArguments(b);
                addPlaceDialogFragment.show(getFragmentManager(), "Nytt gps-sted");
            }
        });

        saveGpsPosition.setEnabled(lastKnownGpsPosition != null);

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
                            TravelService travelService = new TravelService();
                            travelService.getPlaces(searchTerm, new PlaceSearchCollector(placeListAdapter));
                        } catch (IOException e) {
                            YesNoCancel.show(AddPlaceActivity.this, "Oi!!", e.toString(), YesNoCancel.EMPTY, null, null);
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

                try {
                    selectedPlace.checkCompleteness();

                    Bundle b = new Bundle();

                    b.putSerializable("place", selectedPlace);
                    AddPlaceDialogFragment addPlaceDialogFragment = new AddPlaceDialogFragment();
                    addPlaceDialogFragment.setArguments(b);
                    addPlaceDialogFragment.show(getFragmentManager(), "Nytt Ruter-sted");

                } catch (RuntimeException rte) {
                    YesNoCancel.show(AddPlaceActivity.this, "!", rte.getMessage(), YesNoCancel.EMPTY, null, null);
                }
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

            if (newPlace == null) {
                name.setText(newUtmPosition.getUtmNorth() + "/" + newUtmPosition.getUtmEast());
            } else {
                if (newPlace.getHouse() != null) {
                    name.setText(newPlace.getName() + " " + newPlace.getHouse().getName());
                } else {
                    name.setText(newPlace.getName());
                }
            }

            name.setSelected(true);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    try {
                        Locations locations = new Locations(getActivity());
                        String newName = name.getText().toString();
                        if (newPlace == null) {
                            locations.addUtmPosition(newUtmPosition, newName);
                        } else {
                            if (newPlace.getHouse() == null) {
                                newPlace.setName(newName);
                                locations.addPlace(newPlace);
                            } else {
                                House house = newPlace.getHouse();
                                locations.addLocation(house.getX(), house.getY(), newName);
                            }
                        }
                    } catch (Exception e) {
                        Log.e("hjem", "Feil: " + e.toString());
                        YesNoCancel.show(getActivity(), "Oi!!", e.toString(), YesNoCancel.EMPTY, null, null);
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

    private class PlaceSearchCollector extends Collector {
        private PlaceListAdapter placeListAdapter;

        private PlaceSearchCollector(PlaceListAdapter placeListAdapter) {
            this.placeListAdapter = placeListAdapter;
        }

        @Override
        public void setPlaceSearchResult(PlaceSearchResult result) {
            Util.log("PS=" + result.toString());

            if (result.getException() != null) {
                YesNoCancel.show(AddPlaceActivity.this, "Oi!", result.getException().toString(), YesNoCancel.EMPTY, null, null);
            } else {
                placeListAdapter.setList(result.getPlaces());

                if (result.getPlaces().size() == 0) {
                    YesNoCancel.show(AddPlaceActivity.this, "!", "Ingen funnet", YesNoCancel.EMPTY, null, null);
                }
            }
        }
    }

}

