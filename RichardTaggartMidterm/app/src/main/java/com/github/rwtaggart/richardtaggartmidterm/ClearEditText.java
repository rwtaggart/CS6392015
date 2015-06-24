package com.github.rwtaggart.richardtaggartmidterm;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Richard on 6/23/2015.
 */
public class ClearEditText implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        ((EditText)v).setText("", TextView.BufferType.EDITABLE);
    }
}
