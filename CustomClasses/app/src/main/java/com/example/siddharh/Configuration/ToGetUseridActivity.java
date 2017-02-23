package com.example.siddharh.Configuration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.siddharh.webServices.WrapperCustom;

import java.util.HashMap;
import java.util.Map;

public class ToGetUseridActivity extends AppCompatActivity implements View.OnClickListener {
    Button SubmitButton;
    EditText UsernameEdittext;
    public static final String KEY_USERNAME = "username";
    public static final String Key_CHOICE = "choice";
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        UsernameEdittext = (EditText) findViewById(R.id.UsernameEdittext);
        SubmitButton = (Button) findViewById(R.id.LoginButton);
        SubmitButton.setOnClickListener(this);


    }

    private Map<String, String> fetchuserid() {
        username = UsernameEdittext.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_USERNAME, username);
        params.put(Key_CHOICE, "fetchuserid");
        return params;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginButton:
                Map<String, String> Finalparameters = new HashMap<String, String>();
            //    Finalparameters = WrapperCustom.getInstance(this).login(fetchuserid());
                UsernameEdittext.setText(Finalparameters.get("Userid"));
                break;

        }
    }
}
