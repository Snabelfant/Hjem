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
import dag.hjem.model.travelproposal.Section;
import dag.hjem.model.travelproposal.Summary;
import dag.hjem.model.travelproposal.Travel;
import dag.hjem.model.travelproposal.TravelSection;
import dag.hjem.model.travelproposal.WaitingSection;
import dag.hjem.model.travelproposal.WalkingSection;

public class TravelListAdapter extends ArrayAdapter<Travel> {
    private LayoutInflater inflater;

    public TravelListAdapter(Context context) {
        super(context, R.layout.travel);
        inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Travel travel = getItem(position);
        View travelView = inflater.inflate(R.layout.travel, parent, false);
        View summaryView = travelView.findViewById(R.id.travelsummary);

        fillSummary(summaryView, parent, travel.getSummary());

        LinearLayout travelSectionsView = (LinearLayout) travelView.findViewById(R.id.travelsections);
        List<Section> sections = travel.getSections();
        View lastViewIfWalkingView = null;
        View lastViewIfWaitingView = null;
        Nesodden kirke -kattås 05:53 på aker brygge:
        vente 10, gå 10
        for (Section section : sections) {
            View sectionView;
            if (section instanceof WalkingSection) {
                WalkingSection walkingSection = (WalkingSection) section;
                sectionView = inflater.inflate(R.layout.walkingwaitingsection, parent, false);

                TextView walkingTimeView = (TextView) sectionView.findViewById(R.id.walkingsection_walkingtime);
                walkingTimeView.setText(Util.format(walkingSection.getWalkingTime()));

                sectionView.findViewById(R.id.waitingsection_icon).setVisibility(View.GONE);
                sectionView.findViewById(R.id.waitingsection_waitingtime).setVisibility(View.GONE);
                travelSectionsView.addView(sectionView);
                lastViewIfWalkingView = sectionView;
            } else {
                if (section instanceof WaitingSection) {
                    WaitingSection waitingSection = (WaitingSection) section;
                    TextView waitingTimeView;
                    if (lastViewIfWalkingView != null) {
                        lastViewIfWalkingView.findViewById(R.id.waitingsection_icon).setVisibility(View.VISIBLE);
                        waitingTimeView = (TextView) lastViewIfWalkingView.findViewById(R.id.waitingsection_waitingtime);
                        waitingTimeView.setVisibility(View.VISIBLE);
                    } else {
                        sectionView = inflater.inflate(R.layout.walkingwaitingsection, parent, false);
                        sectionView.findViewById(R.id.walkingsection_icon).setVisibility(View.GONE);
                        sectionView.findViewById(R.id.walkingsection_walkingtime).setVisibility(View.GONE);
                        waitingTimeView = (TextView) sectionView.findViewById(R.id.waitingsection_waitingtime);
                        travelSectionsView.addView(sectionView);
                    }
                    waitingTimeView.setText(Util.format(waitingSection.getWaitingTime()));
                } else {
                    final TravelSection travelSection = (TravelSection) section;
                    sectionView = inflater.inflate(R.layout.travelsection, parent, false);

                    TextView departureTime = (TextView) sectionView.findViewById(R.id.travelsection_departuretime);
                    departureTime.setText(travelSection.getDepartureTimeFormatted());

                    TextView departureStop = (TextView) sectionView.findViewById(R.id.travelsection_departurestop);
                    departureStop.setText(travelSection.getDepartureStopName());

                    ImageView typeIconView = (ImageView) sectionView.findViewById(R.id.travelsection_icon);
                    typeIconView.setImageResource(travelSection.getLine().getType().getIconId());

                    TextView lineNameView = (TextView) sectionView.findViewById(R.id.travelsection_lineno);
                    lineNameView.setText(travelSection.getLine().getLineName());

                    TextView destinationView = (TextView) sectionView.findViewById(R.id.travelsection_destination);
                    destinationView.setText(travelSection.getDestination());

                    TextView arrivalTimeView = (TextView) sectionView.findViewById(R.id.travelsection_arriveltime);
                    arrivalTimeView.setText(travelSection.getArrivalTimeFormatted());

                    TextView arrivalStopView = (TextView) sectionView.findViewById(R.id.travelsection_arrivalstop);
                    arrivalStopView.setText(travelSection.getArrivalStopName());

                    TextView travelTimeView = (TextView) sectionView.findViewById(R.id.travelsection_traveltime);
                    travelTimeView.setText(Util.format(travelSection.getTravelTime()));

                    Button deviationsButton = (Button) sectionView.findViewById(R.id.travelsection_deviations);
                    if (travelSection.hasDeviations()) {
                        deviationsButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                YesNoCancel.show(TravelListAdapter.this.getContext(), "Avvik", travelSection.getDeviations(), YesNoCancel.EMPTY, null, null);
                            }
                        });
                    } else {
                        deviationsButton.setVisibility(View.GONE);
                    }


                    travelSectionsView.addView(sectionView);
                }

                lastViewIfWalkingView = null;
            }


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

    public void setTravelList(List<Travel> travels) {
        clear();
        addAll(travels);
        notifyDataSetChanged();

    }

    private void fillSummary(View summaryView, ViewGroup parent, final Summary summary) {
        summaryView.setBackgroundColor(Color.YELLOW);

        TextView departureTimeView = (TextView) summaryView.findViewById(R.id.travelsummary_departuretime);
        departureTimeView.setText(summary.getDepartureTimeFormatted());

        TextView arrivalTimeView = (TextView) summaryView.findViewById(R.id.travelsummary_arrivaltime);
        arrivalTimeView.setText(summary.getArrivalTimeFormatted());

        TextView totalTimeView = (TextView) summaryView.findViewById(R.id.travelsummary_totaltime);
        totalTimeView.setText(Util.formathhmm(summary.getTotalTravelTime()));

        LinearLayout linesView = (LinearLayout) summaryView.findViewById(R.id.travelsummary_lines);

        for (Line line : summary.getLines()) {
            View lineView = inflater.inflate(R.layout.linewithicon, parent, false);

            ImageView typeIconView = (ImageView) lineView.findViewById(R.id.travelsummary_line_icon);
            typeIconView.setImageResource(line.getType().getIconId());
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
