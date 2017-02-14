package com.example.siddharh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.nio.charset.MalformedInputException;

import static com.example.siddharh.myapplication.R.id.activity_processing;

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//THIS CLASS IS NOT IN USE ANYMORE ...THIS FILE WAS IN USE WHEN I STARTED WITH MY WORLD WITH ANDROID
//
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

public class ProcessingActivity extends AppCompatActivity {
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        linearLayout = (LinearLayout)findViewById(R.id.activity_processing);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        String password = intent.getStringExtra("Password");


        if (username.equals("siddharth") && password.equals("1234")) {

            Intent intent1 = new Intent(this,homepage.class);
            intent1.putExtra("Username",username);
            startActivity(intent1);

        } else {
            TextView mytextview = new TextView(this);
            mytextview.setText("Login failed");
            linearLayout.addView(mytextview);
            Intent intent1 = new Intent(this, mainpage.class);
            startActivity(intent1);

        }

    }


}
