package dag.hjem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import dag.hjem.model.location.Location;

public class EditLocationsActivity extends Activity {
    private LocationListAdapter locationListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editlocationsactivity);

        ListView locationList = (ListView) findViewById(R.id.locations);
        try {
            locationListAdapter = new LocationListAdapter(this);
            locationList.setAdapter(locationListAdapter);
        } catch (IOException e) {
            YesNoCancel.show(EditLocationsActivity.this, "!", e.getMessage(), YesNoCancel.EMPTY, null, null);
            return;
        }

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Bundle b = new Bundle();
                    b.putSerializable("locationindex", position);
                    EditLocationDialogFragment editLocationDialogFragment = new EditLocationDialogFragment();
                    editLocationDialogFragment.setArguments(b);
                    editLocationDialogFragment.show(getFragmentManager(), "Endre navn");

                } catch (RuntimeException rte) {
                    YesNoCancel.show(EditLocationsActivity.this, "!", rte.getMessage(), YesNoCancel.EMPTY, null, null);
                }
            }
        });

        locationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = (int) l;
                final Location location = locationListAdapter.getItem(index);
                YesNoCancel.Action sletteAction = new YesNoCancel.Action() {
                    @Override
                    public void doAction(int selection) {
                        try {
                            locationListAdapter.removeLocation(location);
                        } catch (IOException e) {
                            YesNoCancel.show(EditLocationsActivity.this, "!", e.getMessage(), YesNoCancel.EMPTY, null, null);
                        }
                    }
                };
                YesNoCancel.show(EditLocationsActivity.this, null, "Slette '" + location.getName() + "'?", sletteAction, YesNoCancel.EMPTY, null);
                return true;
            }
        });

    }

    public class EditLocationDialogFragment extends DialogFragment {
        public EditLocationDialogFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle bundle = getArguments();

            final int position = bundle.getInt("locationindex");
            final Location location = EditLocationsActivity.this.locationListAdapter.getItem(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View editLocationView = inflater.inflate(R.layout.editlocationdialog, null);
            builder.setView(editLocationView);

            final TextView newNameTextView = (TextView) editLocationView.findViewById(R.id.editlocation_newname);

            newNameTextView.setText(location.getName());
            newNameTextView.setSelected(true);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    try {
                        String newName = newNameTextView.getText().toString();
                        location.setName(newName);
                        EditLocationsActivity.this.locationListAdapter.updateItem(position, location);
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
}

