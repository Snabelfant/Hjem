package dag.hjem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dag.hjem.model.travelproposal.Line;
import dag.hjem.model.travelproposal.PlaceSearchResult;
import dag.hjem.model.travelproposal.Section;
import dag.hjem.model.travelproposal.Summary;
import dag.hjem.model.travelproposal.Travel;
import dag.hjem.model.travelproposal.TravelSearchResult;
import dag.hjem.model.travelproposal.WalkingSection;
import dag.hjem.service.TravelServiceCollector;

public class TravelListAdapter extends ArrayAdapter<Travel> implements TravelServiceCollector {
    private LayoutInflater inflater;

    public TravelListAdapter(Context context) {
        super(context, R.layout.travel);
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<Travel> travels) {
        clear();
        addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Travel travel = getItem(position);
        View travelView = inflater.inflate(R.layout.travel, parent, false);
        View summaryView = travelView.findViewById(R.id.travelsummary);

        fillSummary(summaryView, parent, travel.getSummary());

        LinearLayout travelSectionsView = (LinearLayout) travelView.findViewById(R.id.travelsections);
        List<Section> sections = travel.getSections();
        for (Section section : sections) {
            View sectionView;
            if (section instanceof WalkingSection) {
                WalkingSection walkingSection = (WalkingSection) section;
                sectionView = inflater.inflate(R.layout.walkingsection, parent, false);
                ImageView walkingIconView = (ImageView) sectionView.findViewById(R.id.walkingsection_icon);
                walkingIconView.setImageResource(R.drawable.hjem32);

                TextView walkingTimeView = (TextView) sectionView.findViewById(R.id.walkingsection_walkingtime);
                walkingTimeView.setText(Integer.toString(walkingSection.getWalkingTime()) + " min");

            } else {
                sectionView = inflater.inflate(R.layout.travelsection, parent, false);
                TextView sectionAsText = (TextView) sectionView.findViewById(R.id.travelsection_text);
                sectionAsText.setText(section.toString());
            }
            travelSectionsView.addView(sectionView);
        }

        travelSectionsView.setVisibility(View.GONE);

        travelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout travelSectionsView = (LinearLayout) v.findViewById(R.id.travelsections);
                int currentVisibility = travelSectionsView.getVisibility();
                int newVisibility = currentVisibility == View.VISIBLE ? View.GONE : View.VISIBLE;
                travelSectionsView.setVisibility(newVisibility);
            }
        });
        return travelView;
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

    private void fillSummary(View summaryView, ViewGroup parent, final Summary summary) {
        summaryView.setBackgroundColor(Color.YELLOW);
        String totalTime = new StringBuilder()
                .append(summary.getDepartureTime())
                .append(" - ")
                .append(summary.getArrivalTime())
                .append(" (")
                .append(summary.getTotalTravelTime())
                .append(")").toString();

        TextView totalTimeView = (TextView) summaryView.findViewById(R.id.travelsummary_totaltime);
        totalTimeView.setText(totalTime);

        LinearLayout linesView = (LinearLayout) summaryView.findViewById(R.id.travelsummary_lines);

        for (Line line : summary.getLines()) {
            View lineView = inflater.inflate(R.layout.linewithicon, parent, false);

            ImageView typeIconView = (ImageView) lineView.findViewById(R.id.travelsummary_line_icon);
            typeIconView.setImageResource(R.drawable.buss);
            TextView lineNameView = (TextView) lineView.findViewById(R.id.travelsummary_line_lineno);
            lineNameView.setText(line.getLineName());

            linesView.addView(lineView);
        }

        Button remarksButton = (Button) summaryView.findViewById(R.id.travelsummary_remarks);
        if (summary.hasRemarks()) {
            remarksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    YesNoCancel.show(TravelListAdapter.this.getContext(), "Ruteinfo", summary.getRemarks(), YesNoCancel.EMPTY, null, null);
                }
            });
        } else {
            remarksButton.setVisibility(View.GONE);
        }

    }
}
