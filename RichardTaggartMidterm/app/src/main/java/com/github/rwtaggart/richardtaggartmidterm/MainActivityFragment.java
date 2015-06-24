package com.github.rwtaggart.richardtaggartmidterm;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String ACTIVITY_TAG       = "ConverterActivity";
    private static final double CONVERT_US_TO_CFA  = 586.84;  //  1 Dollar = 586.84 Francs CFA.
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        //  Create action for convert button
        //  NOTE:  This only goes one way: from dollars to francs.
        //  TODO:  Make it go both ways. For now we will just have two buttons.
        //  TODO:  Make conversion happen "live" upon update of editviews.
        Button convertButton = (Button)fragmentView.findViewById(R.id.convert_button);
        if (convertButton != null) {
            convertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //  TODO:  Move this to a new class for listening to clicks. Too crowded here.
                public void onClick(View v) {
                    EditText dollarsText = (EditText) fragmentView.findViewById(R.id.dollars_editText);
                    EditText francsText = (EditText) fragmentView.findViewById(R.id.francs_editText);

                    if (dollarsText == null || francsText == null) {
                        Log.e(ACTIVITY_TAG, "Could not find edit views!");
                        return; //  Not sure if this kind of checking is necessary to print for errors. I'm paranoid.
                    }

                    try {
                        double dollars = Double.parseDouble(dollarsText.getText().toString());
//                        double francs  = Double.parseDouble(francsText.getText().toString());  //  Error, could not parse double.
                        double francs  = 0.0;

                        //  TODO:  Choose which way to perform the conversion.
                        francs = dollars * CONVERT_US_TO_CFA;
                        Log.v(ACTIVITY_TAG, "dollars=" + Double.toString(dollars) + ", francs=" + Double.toString(francs));
                        francsText.setText(Double.toString(francs), TextView.BufferType.EDITABLE);

                    } catch (NumberFormatException e) {
                        Log.e(ACTIVITY_TAG, "Could not parse string.", e);
                        Context context = v.getContext();
                        Toast.makeText(context, "Could not parse double! Try again.", Toast.LENGTH_SHORT);
                    }
                }
            });

            EditText dollarsText = (EditText) fragmentView.findViewById(R.id.dollars_editText);
            if (dollarsText != null)
                dollarsText.setOnClickListener(new ClearEditText());

            EditText francsText = (EditText) fragmentView.findViewById(R.id.francs_editText);
            if (francsText != null)
                francsText.setOnClickListener(new ClearEditText());
        }

        return fragmentView;
    }
}
