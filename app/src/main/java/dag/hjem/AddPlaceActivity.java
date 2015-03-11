package dag.hjem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.ruter.api.RuterApi;
import dag.hjem.service.TravelService;
import dag.hjem.service.TravelServiceCollector;

public class AddPlaceActivity extends Activity {
//    private ListView varelisteView;
//    private VarelisteAdapter varelisteAdapter;
//    private EndreVareDialogFragment endreVareDialogFragment;

    private TravelService travelService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplaceactivity);

        EditText searchTermView = (EditText) findViewById(R.id.placesearchterm);
//        searchTermView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        final TextView result = (TextView) findViewById(R.id.tmpsearchresult);

        searchTermView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (true /*actionId == EditorInfo.IME_ACTION_SEARCH*/) {
                    String searchTerm = textView.getText().toString().trim();

                    if (!"".equals(searchTerm)) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                        try {
                            travelService.getPlaces(searchTerm);
                        } catch (IOException e) {
                            result.setText(e.toString());
                        }

                    }

                    return true;
                }

                return false;
            }
        });

        PlaceSearchColletor placeSearchColletor = new PlaceSearchColletor(result);
        travelService = new TravelService(new RuterApi(), placeSearchColletor);
        setContentView(R.layout.addplaceactivity);

//        this.varelisteView = (ListView) findViewById(R.id.vareliste);
//        varelisteAdapter = VarelisteAdapter.opprett(this);
//
//        this.varelisteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                endreVareDialogFragment = new EndreVareDialogFragment((int) l);
//                endreVareDialogFragment.show(getFragmentManager(), "Endre vare");
//            }
//        });
//
//        this.varelisteView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                slettVare(l);
//                return true;
//            }
//        });
//
//        this.varelisteView.setAdapter(varelisteAdapter);
//
    }

    private void removePlace(long index) {
//        final Varelistevare varelistevare = varelisteAdapter.getItem((int)index);
//        YesNoCancel.Action sletteAction = new YesNoCancel.Action() {
//            @Override
//            public void doAction(int selection) {
//                varelisteAdapter.slett(varelistevare);
//            }
//        };
//        YesNoCancel.show(this, null, "Slette '" + varelistevare.getBetegnelse() + "'?", sletteAction, YesNoCancel.EMPTY, null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        String bildefilnavn = data.getExtras().getString("bildefilnavn");
//        endreVareDialogFragment.setBildefilnavn(bildefilnavn);
//        Logg.d("bildefilnavn satt");
    }


//    public class NyVarelistevareDialogFragment extends DialogFragment {
//        public NyVarelistevareDialogFragment() {
//        }
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//            LayoutInflater inflater = (LayoutInflater)  getApplicationContext()
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View nyVareView = inflater.inflate(R.layout.nyellerendrevarelistevare, null);
//            builder.setView(nyVareView);
//
//            final TextView innPlassering = (TextView) nyVareView.findViewById(R.id.innVarelistevareplassering);
//            final TextView innBetegnelse = (TextView) nyVareView.findViewById(R.id.innVarelistevarebetegnelse);
//
//            nyVareView.findViewById(R.id.innBildefil).setVisibility(View.GONE);
//            nyVareView.findViewById(R.id.nyttbildebutton).setVisibility(View.GONE);
//            nyVareView.findViewById(R.id.slettbildebutton).setVisibility(View.GONE);
//
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    final String betegnelse = innBetegnelse.getText().toString();
//                    final String plassering = innPlassering.getText().toString();
//
//                    try {
//                        varelisteAdapter.leggTil(betegnelse, plassering);
//                    } catch (UgyldigVareException uve) {
//                        Logg.d( "Feil: " + uve.getMessage());
//                        YesNoCancel.show(VarelisteActivity.this,"Ugyldig vare", uve.getMessage(),YesNoCancel.EMPTY,null, null);
//                        return;
//                    }
//
//                }
//            })
//                    .setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
//
//            return builder.create();
//        }
//    }

    private class PlaceSearchColletor implements TravelServiceCollector {
        private TextView resultView;

        public PlaceSearchColletor(TextView resultView) {
            this.resultView = resultView;
        }

        @Override
        public void setTravelSearchResult(TravelSearchResult result) {

        }

        @Override
        public void setPlaceSearchResult(PlaceSearchResult result) {
            Toast.makeText(AddPlaceActivity.this, "S=" + result.toString(), Toast.LENGTH_LONG).show();

            resultView.setText(result.toString());
        }
    }
}

