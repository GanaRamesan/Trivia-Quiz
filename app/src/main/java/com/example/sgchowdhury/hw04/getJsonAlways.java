package com.example.sgchowdhury.hw04;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by sg chowdhury on 03-10-2017.
 */

public class getJsonAlways extends AsyncTask<RequestParam,Void,Void> {
    static StringBuilder sb = new StringBuilder();
    Context context;

    public getJsonAlways(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
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
        context.startActivity(i1);
    }

    @Override
    protected Void doInBackground(RequestParam... requestParams) {
        BufferedReader reader = null;
        try {
            HttpURLConnection con = requestParams[0].setUpCOnnectio();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));


            String line = "";
            while ((line = reader.readLine()) != null) {

                sb.append(line);

            }
            return null;
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
