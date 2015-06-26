package com.github.rwtaggart.asynctaskproject;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.URL;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        String imageLoc = "https://www.github.com/rwtaggart/CS6392015/blob/master/BabylonNY.jpg";
//        String imageLoc = "http://raw.githubusercontent.com/rwtaggart/CS6392015/master/BabylonNY.jpg";
        String imageLoc = "https://github.com/rwtaggart/CS6392015/blob/master/BabylonNY.jpg";
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.setViewToUpdate((ImageView) fragmentView.findViewById(R.id.imageView));
        imageDownloader.execute(imageLoc);
        return fragmentView;
    }
}
