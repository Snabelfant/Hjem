package dag.hjem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dag.hjem.ruter.model.TravelProposal;

/**
 * Created by Dag on 16.03.2015.
 */
public class TravelProposalFragment extends Fragment {
    private TravelProposal travelProposal;
    private String s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);

        Bundle data = getArguments();
//        travelProposal = (TravelProposal) data.getSerializable("travelproposal");
        s = (String) data.getSerializable("travelproposal");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.travelproposalfragment, container, false);
        TextView tv = (TextView) v.findViewById(R.id.textView1);
        tv.setText(s);
        return v;
    }
}
