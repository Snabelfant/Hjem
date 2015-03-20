package dag.hjem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.Section;
import dag.hjem.model.travelproposal.Travel;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.service.TravelServiceCollector;

public class TravelListAdapter extends ArrayAdapter<Travel> implements TravelServiceCollector {
    private LayoutInflater inflater;

    public TravelListAdapter(Context context) {
        super(context, R.layout.travellistrow);
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<Travel> travels) {
        clear();
        addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Travel travel = getItem(position);

        View rowView = inflater.inflate(R.layout.travellistrow, parent, false);

        View summaryView = inflater.inflate(R.layout.travelsection, parent, false);
        TextView summaryName = (TextView) summaryView.findViewById(R.id.travelsection_summary);
        summaryName.setText(travel.getSummary());
        summaryName.setBackgroundColor(Color.YELLOW);
        ((LinearLayout) rowView).addView(summaryView);

        List<Section> sections = travel.getSections();
        for (Section section : sections) {
            View sectionView = inflater.inflate(R.layout.travelsection, parent, false);
            TextView sectionName = (TextView) sectionView.findViewById(R.id.travelsection_summary);
            sectionName.setText(section.toString());
            ((LinearLayout) rowView).addView(sectionView);
        }

        return rowView;
    }

    @Override
    public void setTravelSearchResult(TravelSearchResult result) {
        clear();
        addAll(result.getTravels());
        notifyDataSetChanged();

    }

    @Override
    public void setPlaceSearchResult(PlaceSearchResult result) {
    }

}
