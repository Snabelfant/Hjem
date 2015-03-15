package dag.hjem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dag.hjem.model.travelproposal.Place;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.service.TravelServiceCollector;

public class PlaceListAdapter extends ArrayAdapter<Place> implements TravelServiceCollector {
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
        Place place = getItem(position);

        View rowView = inflater.inflate(R.layout.placelistrow, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.placelist_name);
        TextView district = (TextView) rowView.findViewById(R.id.placelist_district);
        TextView type = (TextView) rowView.findViewById(R.id.placelist_type);

        name.setText(place.getName());
        district.setText(place.getDistrict());
        type.setText(place.getType());

        return rowView;
    }

    @Override
    public void setTravelSearchResult(TravelSearchResult result) {

    }

    @Override
    public void setPlaceSearchResult(PlaceSearchResult result) {
        clear();
        addAll(result.getPlaces());
        notifyDataSetChanged();
    }
}
