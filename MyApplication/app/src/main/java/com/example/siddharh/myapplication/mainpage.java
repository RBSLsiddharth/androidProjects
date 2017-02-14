package com.example.siddharh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mainpage extends AppCompatActivity {
Button btsubmit;
Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
System.out.println("i came in oncreate method");

        btsubmit = (Button)findViewById(R.id.button);
        btsubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Login();
          }
      });
        btnsubmit = (Button)findViewById(R.id.button4);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    System.out.println("I came in start function");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    System.out.println("i came in Restart function");
    }

    @Override
    protected void onStop() {
        super.onStop();
    System.out.println("i came in stop method");

    }

    @Override
    protected void onResume() {
        super.onResume();
    System.out.println("i came in resume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
    System.out.println("i came in pause method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    System.out.println("i came in destroy method");
    }

    public void Login(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        
    }

     public void Signup(){
         System.out.println("I CAMe uprtiaaaasld");
         Intent intent = new Intent(this, mainpage.class);
         startActivity(intent);
     }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_ENTER:
               Toast.makeText(this,"YOU ENTERED ENTERed",Toast.LENGTH_LONG).show();
                return true;


        }
        return  true;
    }

}

