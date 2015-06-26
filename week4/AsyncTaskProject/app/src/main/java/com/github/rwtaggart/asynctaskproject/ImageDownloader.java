package com.github.rwtaggart.asynctaskproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Richard on 6/21/2015.
 */
public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    private ImageView iv;

    void setViewToUpdate(ImageView imgView)
    {
        this.iv = imgView;
    }
    @Override
    /**
     * @brief Download image from URL params. Populate ImageView with this.
     * This function is adapted from "Beginning Android Programming" by K. Grant. Chapter 4 AsyncTask example.
     * @param Expecting array of length 1.
     */
    protected Bitmap doInBackground(String... params) {
        try {
            if (params.length != 1) {
                throw new ImageLoaderException("Only expecting one argument. ");
            }

            URL url = new URL(params[0]);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            if (httpCon.getResponseCode() != 200) {
                throw new ImageLoaderException("Failed to connect to " + url.getPath());
            }

            InputStream is = httpCon.getInputStream();
            Log.i("Image", "Finished downloading image.");
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Image", "Failed to load image. -- " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        super.onPostExecute(img);
        if (iv != null && img != null) {
            iv.setImageBitmap(img);
            Log.i("Image", "Finished updating ImageView with picture.");
        } else {
            Log.e("Image", "Failed to update ImageView. Null Pointer");
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        super.onCancelled(bitmap);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    class ImageLoaderException extends Exception{
        ImageLoaderException(String message) {
            super(message);
        }
    }
}
