package com.example.sgchowdhury.hw04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StatsActivity extends AppCompatActivity {
    double percent;
    @Override
    protected void onPostResume() {
        super.onPostResume();
        percent = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarCorrect);
        pb.setProgress(0);
         percent = (double) TriviaActivity.rightanswers/(double) TriviaActivity.totalanswers;
        Log.d("demo123",TriviaActivity.rightanswers+"");
        percent = percent*100;
        TextView tv = (TextView) findViewById(R.id.textViewPerct);
        tv.setText(percent+"%");
        pb.setMax(100);
        pb.setProgress((int) (percent));

        final Button finish = (Button) findViewById(R.id.buttonQuit);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        final Button tryagain = (Button) findViewById(R.id.buttonTry);
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               RequestParam rp = new RequestParam("GET", "http://dev.theappsdr.com/apis/trivia_json/index.php");


                new getJsonAlways(getApplicationContext()).execute(rp);




            }
        });




    }
}
