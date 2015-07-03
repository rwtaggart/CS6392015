package com.github.rwtaggart.imageviewer;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{
    private              int imageIndex   = 0;  //!<  Start at the first one.
    private static final int MAX_N_IMAGES = 6;  //!<  Specify max number of images used.
    private              int imageArray[][];    //!<  Used to store
    private static final int IMAGE_IDX  = 0;    //!<  "zero" index for image resources
    private static final int STRING_IDX = 1;    //!<  "1st"  index for string resources

    private View fragmentView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        imageArray = new int[][] {
                  {R.mipmap.tree_buggy, R.string.tree_buggy_caption}
                , {R.mipmap.tree_sports_car, R.string.tree_sports_car_caption}
                , {R.mipmap.tree_truck_log, R.string.tree_truck_caption}
                , {R.mipmap.tree_boat,  R.string.tree_boat_caption}
                , {R.mipmap.tree_train, R.string.tree_train_caption}
                , {R.mipmap.tree_air,   R.string.tree_air_caption}
                , {R.mipmap.pine_tree_row, R.string.tree_rows_caption}
                , {R.mipmap.tree_driving,  R.string.tree_driving_caption}
        };

        Log.d("IMAGESETUP", "array.length = " + Integer.toString(imageArray.length));

        ImageView imageV = (ImageView) fragmentView.findViewById(R.id.imageView);
        TextView  textV  = (TextView)  fragmentView.findViewById(R.id.textView);

        setViews(0, imageV, textV);

        Button nextButton = (Button) fragmentView.findViewById(R.id.next_button);
        if (nextButton != null)
            nextButton.setOnClickListener(this);
        else
            Log.e("IMAGE", "Could not find next button.");

        Button prevButton = (Button) fragmentView.findViewById(R.id.prev_button);
        if (prevButton != null)
            prevButton.setOnClickListener(this);
        else
            Log.e("IMAGE", "Could not find prev button.");

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        Log.v("IMAGEBUTTON", "Clicked button" + Integer.toString(v.getId()));
        int idx = 0;
        if (v.getId() == R.id.next_button)
            idx = nextImage();
        else if (v.getId() == R.id.prev_button)
            idx = prevImage();
        else
            Log.e("IMAGEVIEW", "Neither next nor prev buttons pressed.");

        ImageView imageV = (ImageView) fragmentView.findViewById(R.id.imageView);
        TextView  textV  = (TextView)  fragmentView.findViewById(R.id.textView);

        Log.d("IMAGEVIEW", "index = " + Integer.toString(idx));
        setViews(idx, imageV, textV);
    }

    public void setViews(int arrayIdx, ImageView imageV, TextView textV) {
        if (imageV != null)
            imageV.setImageResource(imageArray[arrayIdx][IMAGE_IDX]);

        if (textV != null)
            textV.setText(imageArray[arrayIdx][STRING_IDX]);
    }

    public int nextImage() {
        imageIndex++;
        if (imageIndex >= imageArray.length)
            imageIndex = 0;
        Log.d("IMAGENEXT", "next img idx = " + Integer.toString(imageIndex));
        return imageIndex;
    }

    public int prevImage() {
        imageIndex--;
        if (imageIndex < 0)
            imageIndex = imageArray.length - 1;
        Log.d("IMAGENEXT", "prev img idx = " + Integer.toString(imageIndex));
        return imageIndex;
    }
}
