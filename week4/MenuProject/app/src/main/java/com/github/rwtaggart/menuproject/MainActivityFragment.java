package com.github.rwtaggart.menuproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button smsButton = (Button)getView().findViewById(R.id.sms_button);
        if (smsButton != null)
            smsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.setData(Uri.parse("smsto:" + Uri.encode("8005555555")));
                    smsIntent.putExtra("sms_body", "Hello. This is a message.");
                    startActivity(smsIntent);
                }
            });

        Button phoneButton = (Button)getView().findViewById(R.id.phone_button);
        if (phoneButton != null)
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                    phoneIntent.setData(Uri.parse("tel:8887776543"));
                    startActivity(phoneIntent);
                }
            });

        Button webButton = (Button)getView().findViewById(R.id.web_button);
        if (webButton != null)
            webButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                    startActivity(webIntent);
                }
            });

        Button mapsButton = (Button)getView().findViewById(R.id.map_button);
        if (mapsButton != null)
            mapsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String geoUri = String.format("geo:38.847231, -43.032312");
                    Uri geo = Uri.parse(geoUri);
                    Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
                    startActivity(geoMap);
                }
            });

        Button shareButton = (Button)getView().findViewById(R.id.share_button);
        if (shareButton != null)
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent chooserIntent = new Intent(Intent.ACTION_SEND);
                    chooserIntent.setType("text/plain");
                    chooserIntent.putExtra(Intent.EXTRA_INTENT, "CS639");
                    chooserIntent.putExtra(Intent.EXTRA_TEXT, "Join CS639");
                    startActivity(Intent.createChooser(chooserIntent, "Share the love"));
                }
            });

        Button newActButton = (Button)getView().findViewById(R.id.nact_button);
        if (newActButton != null)
            newActButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newActivityIntent = new Intent(getActivity(), NewActivity.class);
                    startActivity(newActivityIntent);
                }
            });

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
