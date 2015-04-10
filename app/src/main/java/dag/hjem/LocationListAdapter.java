package dag.hjem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;

import dag.hjem.model.location.Location;
import dag.hjem.model.location.Locations;

public class LocationListAdapter extends ArrayAdapter<Location> {
    private LayoutInflater inflater;
    private Locations locations;

    public LocationListAdapter(Context context) throws IOException {
        super(context, R.layout.editlocationrow);
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        locations = new Locations(context);
        addAll(locations.getAllLocations());
    }

    //    public void setList(List<Location> locations) {
//        clear();
//        addAll(locations);
//        notifyDataSetChanged();
//    }
//
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Location location = getItem(position);

        View rowView = inflater.inflate(R.layout.editlocationrow, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.editlocation_name);
        TextView details = (TextView) rowView.findViewById(R.id.editlocation_details);
        name.setText(location.getName());
        details.setText(location.getDetails());
        return rowView;
    }

    public void updateItem(int index, Location location) throws IOException {
        locations.update(index, location);
        notifyDataSetChanged();
    }


    public void removeLocation(Location location) throws IOException {
        super.remove(location);
        locations.remove(location);
    }
}