package dag.hjem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import dag.hjem.model.travelproposal.House;
import dag.hjem.model.travelproposal.HouseSearchResult;
import dag.hjem.model.travelproposal.Place;
import dag.hjem.model.travelproposal.PlaceType;
import dag.hjem.service.Collector;
import dag.hjem.service.TravelService;

public class PlaceListAdapter extends ArrayAdapter<Place> {
    private LayoutInflater inflater;

    public PlaceListAdapter(Context context) {
        super(context, R.layout.placelistrow);
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<Place> newPlaces) {
        clear();
        addAll(newPlaces);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Place place = getItem(position);

        View rowView = inflater.inflate(R.layout.placelistrow, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.placelist_name);
        TextView district = (TextView) rowView.findViewById(R.id.placelist_district);
        TextView type = (TextView) rowView.findViewById(R.id.placelist_type);
        Spinner houseNoSpinner = (Spinner) rowView.findViewById(R.id.placelist_houseno_spinner);
        houseNoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place.setHouse((House) parent.getAdapter().getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button houseNoButton = (Button) rowView.findViewById(R.id.placelist_houseno_button);
        if (place.getType() == PlaceType.STREET) {
            houseNoButton.setVisibility(View.VISIBLE);
            houseNoButton.setOnClickListener(new HouseSelectionButtonListener(place, houseNoSpinner));
        }

        name.setText(place.getName());
        district.setText(place.getDistrict());
        type.setText(place.getType().getDescription());

        return rowView;
    }

    private class HouseSelectionButtonListener implements Button.OnClickListener {
        private Place place;
        private Spinner houseNoSpinner;

        private HouseSelectionButtonListener(Place place, Spinner houseNoSpinner) {
            this.place = place;
            this.houseNoSpinner = houseNoSpinner;
        }

        @Override
        public void onClick(View v) {
            v.setVisibility(View.GONE);

            ArrayAdapter<House> houseNoAdapter = new ArrayAdapter<>(PlaceListAdapter.this.getContext(),
                    R.layout.travelselectionspinner);
            houseNoAdapter.setDropDownViewResource(R.layout.travelselectionspinner);
            houseNoSpinner.setAdapter(houseNoAdapter);
            houseNoSpinner.setVisibility(View.VISIBLE);
            try {
                TravelService travelService = new TravelService();
                travelService.getHouses(place.getRuterId(), new HouseSearchCollector(houseNoAdapter, place));
            } catch (IOException e) {
                YesNoCancel.show(PlaceListAdapter.this.getContext(), "Oi!!", e.toString(), YesNoCancel.EMPTY, null, null);
            }
        }


        private class HouseSearchCollector extends Collector {
            private ArrayAdapter<House> houseNoAdapter;
            private Place place;

            private HouseSearchCollector(ArrayAdapter<House> houseNoAdapter, Place place) {
                this.houseNoAdapter = houseNoAdapter;
                this.place = place;
            }

            @Override
            public void setHouseSearchResult(HouseSearchResult result) {
                if (result.getException() != null) {
                    YesNoCancel.show(PlaceListAdapter.this.getContext(), "Oi!", result.getException().toString(), YesNoCancel.EMPTY, null, null);
                } else {
                    houseNoAdapter.addAll(result.getHouses());

                    if (result.getHouses().size() == 0) {
                        YesNoCancel.show(PlaceListAdapter.this.getContext(), "!", "Ingen forslag", YesNoCancel.EMPTY, null, null);
                    } else {
                        place.setHouse(result.getHouses().get(0));
                    }
                }
            }
        }
    }
}