package com.example.sgchowdhury.hw04;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questionList = new ArrayList<>();
    RequestParam rp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isCOnnected()) {

            rp = new RequestParam("GET", "http://dev.theappsdr.com/apis/trivia_json/index.php");


            new DownloadJsonAsync(this,MainActivity.this).execute(rp);



        } else {
            Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
        }

        Button exit = (Button) findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();  // to finish current activity and all parent activity
                System.exit(0);
            }
        });

    }








    private boolean isCOnnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


}