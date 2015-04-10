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

import dag.hjem.model.travelproposal.DepartureOrArrivalTime;
import dag.hjem.model.travelproposal.Line;
import dag.hjem.model.travelproposal.RealtimeCall;
import dag.hjem.model.travelproposal.RealtimeSearchResult;
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
//        Nesodden kirke -kattås 05:53 på aker brygge:   vente 10, gå 10
        for (Section section : sections) {
            View sectionView;
            if (section instanceof WalkingSection) {
                WalkingSection walkingSection = (WalkingSection) section;
                sectionView = inflater.inflate(R.layout.walkingwaitingsection, parent, false);

                TextView walkingTimeView = (TextView) sectionView.findViewById(R.id.walkingsection_walkingtime);
                walkingTimeView.setText(Util.format(walkingSection.getWalkingTime()));
                walkingTimeView.setBackgroundColor(Settings.Color.walkingSection);
                sectionView.setBackgroundColor(Settings.Color.walkingSection);
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
                        sectionView.setBackgroundColor(Settings.Color.walkingSection);

                        travelSectionsView.addView(sectionView);
                    }
                    waitingTimeView.setText(Util.format(waitingSection.getWaitingTime()));
                } else {
                    final TravelSection travelSection = (TravelSection) section;
                    sectionView = inflater.inflate(R.layout.travelsection, parent, false);

                    setDepartureOrArrivalTime(travelSection.getDepartureTime(), sectionView, R.id.travelsection_departuretime);

                    TextView departureStop = (TextView) sectionView.findViewById(R.id.travelsection_departurestop);
                    departureStop.setText(travelSection.getDepartureStopName());

                    ImageView typeIconView = (ImageView) sectionView.findViewById(R.id.travelsection_icon);
                    typeIconView.setImageResource(travelSection.getLine().getType().getIconId());

                    TextView lineNameView = (TextView) sectionView.findViewById(R.id.travelsection_lineno);
                    lineNameView.setText(travelSection.getLine().getLineName());

                    TextView destinationView = (TextView) sectionView.findViewById(R.id.travelsection_destination);
                    destinationView.setText(travelSection.getDestination());

                    setDepartureOrArrivalTime(travelSection.getArrivalTime(), sectionView, R.id.travelsection_arriveltime);

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

    public void updateRealtime(RealtimeSearchResult realtimeSearchResult) {
        List<RealtimeCall> realtimeCalls = realtimeSearchResult.getRealtimeCalls();
        if (realtimeCalls.size() == 0) {
            return;
        }

        String lineNo = realtimeSearchResult.getLineNo();
        int ruterStopId = realtimeSearchResult.getRuterStopId();

        for (RealtimeCall realtimeCall : realtimeCalls) {

            for (int i = 0; i < getCount(); i++) {
                Travel travel = getItem(i);
                if (travel.setRealtime(lineNo, ruterStopId, realtimeCall)) {
                    break;
                }

            }
        }
        notifyDataSetChanged();
    }

    private void fillSummary(View summaryView, ViewGroup parent, final Summary summary) {
        summaryView.setBackgroundColor(Settings.Color.summary);

        setDepartureOrArrivalTime(summary.getDepartureTime(), summaryView, R.id.travelsummary_departuretime);
        setDepartureOrArrivalTime(summary.getArrivalTime(), summaryView, R.id.travelsummary_arrivaltime);

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

    private void setDepartureOrArrivalTime(DepartureOrArrivalTime departureOrArrivalTime, View textView, int id) {
        TextView view = (TextView) textView.findViewById(id);
        view.setText(departureOrArrivalTime.format());
        if (departureOrArrivalTime.isRealtime()) {
            view.setTextColor(Color.GREEN);
        } else {
            view.setTextColor(Color.BLUE);
        }
    }
}
