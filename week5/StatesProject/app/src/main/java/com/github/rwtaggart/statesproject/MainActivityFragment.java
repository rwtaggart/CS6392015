package com.github.rwtaggart.statesproject;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {
    public  final static String LAT_KEY  = "latCoord";
    public  final static String LONG_KEY = "longCoord";

    private final static String SELECTION_LETTER_BEGIN = "P";
    private final static double PA_LAT                 = 40.285670;
    private final static double PA_LONG                = -76.889458;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.states_entries, R.layout.list_element);
        setListAdapter(adapter);
        return fragmentView;

    }

    @Override
    public void onListItemClick(ListView l, View clickedView, int position, long id) {
        super.onListItemClick(l, clickedView, position, id);
        TextView tv = (TextView) clickedView;
        String clickText = "List Item " + tv.getText() + " was clicked!";
        Toast.makeText(getActivity().getBaseContext(), clickText, Toast.LENGTH_SHORT).show();

        if (tv.getText().toString().startsWith(SELECTION_LETTER_BEGIN)) {
            //  We have the one that we want!
            Intent mapsIntent = new Intent(getActivity(), MapsActivity.class);
            Bundle coordBundle = new Bundle();
            coordBundle.putDouble(LAT_KEY,  PA_LAT);
            coordBundle.putDouble(LONG_KEY, PA_LONG);
            mapsIntent.putExtras(coordBundle);
            startActivity(mapsIntent);
        }
    }
}
