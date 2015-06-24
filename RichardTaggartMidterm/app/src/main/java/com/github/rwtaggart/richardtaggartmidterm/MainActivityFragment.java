package com.github.rwtaggart.richardtaggartmidterm;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private static final String ACTIVITY_TAG       = "ConverterActivity";
    enum eConversionMethod  {cTO_DOLLARS, cTO_FRANCS};
    private static final double CONVERT_US_TO_CFA  = 586.84;  //  1 Dollar = 586.84 Francs CFA.
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        //  Create action for convert button and EditViews.
        //  NOTE:  This only goes one way: from dollars to francs.
        //  TODO:  Make conversion happen "live" upon update of EditView's.
        Button convertButton = (Button)fragmentView.findViewById(R.id.convert_button);
        if (convertButton != null)
            convertButton.setOnClickListener(MainActivityFragment.this);

        EditText dollarsText = (EditText) fragmentView.findViewById(R.id.dollars_editText);
        if (dollarsText != null)
            dollarsText.setOnClickListener(new ClearEditText());

        EditText francsText = (EditText) fragmentView.findViewById(R.id.francs_editText);
        if (francsText != null)
            francsText.setOnClickListener(new ClearEditText());

        //  Populate Spinner object with options.
        Spinner convertSpinner = (Spinner) fragmentView.findViewById(R.id.convert_spinner);
        if (convertSpinner != null)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.converter_items, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            convertSpinner.setAdapter(adapter);
        }

        return fragmentView;
    }



    @Override
    public void onClick(View v) {
        View fragmentView = getView();
        EditText dollarsText = (EditText) fragmentView.findViewById(R.id.dollars_editText);
        EditText francsText = (EditText) fragmentView.findViewById(R.id.francs_editText);

        if (dollarsText == null || francsText == null) {
            Log.e(ACTIVITY_TAG, "Could not find edit views!");
            return; //  Not sure if this kind of checking is necessary to print for errors. I'm paranoid.
        }

        Resources res = getResources();
        String[] convert_spinner_array = res.getStringArray(R.array.converter_items);
        eConversionMethod convMethod = eConversionMethod.cTO_FRANCS;  //  Initialize to a default.

        Spinner convertSpinner = (Spinner) fragmentView.findViewById(R.id.convert_spinner);
        if (convertSpinner == null ) {
            Log.e(ACTIVITY_TAG, "Could not find spinner. Using dollars->francs as default.");
        }

        //  Find the selection for conversion. We probably don't need enum's, but I used them anyway.
        //  NOTE:  The order that we display the items in XML matters here!!! We should probably find a way to fix that.
        if (convertSpinner.getSelectedItem() != null)
            if (convertSpinner.getSelectedItem().toString().equals(convert_spinner_array[0]))
                convMethod = eConversionMethod.cTO_FRANCS;
            else if (convertSpinner.getSelectedItem().toString().equals(convert_spinner_array[1]))
                convMethod = eConversionMethod.cTO_DOLLARS;
            else
                Log.e(ACTIVITY_TAG, "Could not match spinner item. Using dollars->francs as default. " + "spinnerItem=" + convertSpinner.getSelectedItem().toString() + ", match[0]="    + convert_spinner_array[0] + ", match[1]="    + convert_spinner_array[1]);
        else
            Log.e(ACTIVITY_TAG, "No Item Selected.");

        Log.v(ACTIVITY_TAG, "convMethod=" + convMethod.toString());
        try {
            DecimalFormat dformat = new DecimalFormat("0.00");
            double dollars, francs;
            double conversion = CONVERT_US_TO_CFA;
            if (convMethod == eConversionMethod.cTO_FRANCS) {
                dollars = Double.parseDouble(dollarsText.getText().toString());
                conversion = CONVERT_US_TO_CFA;
                francs = dollars * conversion;
            } else {
                francs  = Double.parseDouble(francsText.getText().toString());
                conversion = 1 / CONVERT_US_TO_CFA;  //  We want the inverse here.
                dollars = francs * conversion;
            }

            Log.v(ACTIVITY_TAG, "dollars=" + Double.toString(dollars) + ", francs=" + Double.toString(francs) + ", conversion=" + Double.toString(conversion));

            dollarsText.setText ("$" + dformat.format(dollars), TextView.BufferType.EDITABLE);
            francsText.setText  (dformat.format(francs), TextView.BufferType.EDITABLE);

        } catch (NumberFormatException e) {
            Log.e(ACTIVITY_TAG, "Could not parse string.", e);
            Context context = v.getContext();
            Toast.makeText(context, "Could not parse double! Try again.", Toast.LENGTH_SHORT);
        }
    }
}
