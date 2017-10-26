package com.example.sgchowdhury.hw04;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sg chowdhury on 29-09-2017.
 */


public class DownloadJsonAsync extends AsyncTask<RequestParam, Integer, String> {
    static StringBuilder sb = new StringBuilder();
    private Context context;
    private Activity activity;

    @Override
    protected void onPostExecute(String s) {




            Button button = activity.findViewById(R.id.buttonStart);
            ImageView trivia = activity.findViewById(R.id.imageViewLaunch);
            button.setEnabled(true);
            trivia.setImageDrawable(context.getDrawable(R.drawable.trivia));
            ProgressBar pb = activity.findViewById(R.id.progressBarTrivia);
            pb.setVisibility(View.GONE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<Question> questionDetails = new ArrayList<>();
                    try {
                        questionDetails = QuestionUtil.parseQuestion(sb.toString());
                        Log.d("ans",questionDetails.size()+"");
                    }catch(Exception e)
                    {

                    }
                    Intent i1 = new Intent (context, TriviaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("json",questionDetails);
                    i1.putExtras(bundle);
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i1);

                }
            });










    }

    public DownloadJsonAsync(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected String doInBackground(RequestParam... strings) {

        BufferedReader reader = null;
        try {
            HttpURLConnection con = strings[0].setUpCOnnectio();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));


            String line = "";
            while ((line = reader.readLine()) != null) {

                sb.append(line);

            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}