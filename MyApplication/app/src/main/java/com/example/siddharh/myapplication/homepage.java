package com.example.siddharh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.attr.onClick;

public class homepage extends AppCompatActivity implements View.OnClickListener  {
RelativeLayout linearLayout;
  Button  btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        linearLayout = (RelativeLayout)findViewById(R.id.activity_homepage);

        TextView mytextview = new TextView(this);
        mytextview.setText("Login successful");
        linearLayout.addView(mytextview);
        btnsubmit = (Button)findViewById(R.id.Hitbutton);
        btnsubmit.setOnClickListener(this);
    }
    public void hitbutton(){
        String customAcion = "android.intent.action.MYACTION";

        Intent intent = new Intent();
        intent.setAction(customAcion);
        startActivity(intent);
    }
        public void onClick(View v){
            switch(v.getId()){
                case R.id.Hitbutton: hitbutton();
                                    break;
            }
        }

}
