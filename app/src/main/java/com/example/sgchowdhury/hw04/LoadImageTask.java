
package com.example.sgchowdhury.hw04;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Void> {
    private Context context;
    private Activity activity;
    Bitmap bmp;
    public LoadImageTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.findViewById(R.id.progressBarQues).setVisibility(View.VISIBLE);
        ImageView im = activity.findViewById(R.id.imageViewQues);
        im.setImageDrawable(null);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ImageView im = activity.findViewById(R.id.imageViewQues);
        im.setImageBitmap(bmp);
        activity.findViewById(R.id.progressBarQues).setVisibility(View.INVISIBLE);
    }

    @Override
    protected Void doInBackground(String... args) {

        try {

             bmp =  BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}