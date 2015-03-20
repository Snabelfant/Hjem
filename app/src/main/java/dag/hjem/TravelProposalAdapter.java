package dag.hjem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import dag.hjem.model.travelproposal.Travel;


public class TravelProposalAdapter extends FragmentPagerAdapter {
    private List<Travel> travelProposals = null;

    public TravelProposalAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setTravelProposals(List<Travel> travelProposals) {
        this.travelProposals = travelProposals;
        notifyDataSetChanged();
    }

    public Fragment getItem(int arg0) {
        Log.i("hjem", "GI=" + arg0);
        TravelProposalFragment fragment = new TravelProposalFragment();
        Bundle data = new Bundle();
        data.putSerializable("travelproposal", travelProposals.get(arg0).toString());
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public int getCount() {
        return travelProposals == null ? 0 : travelProposals.size();
    }
}