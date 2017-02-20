package com.example.siddharh.Configuration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.siddharh.webServices.WrapperCustom;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button SubmitButton;
    EditText UsernameEdittext;
    EditText PasswordEditext;
    public static final String KEY_USERNAME = "username";
    public static final String Key_PASSWORD = "password";
    public static final String Key_CHOICE = "choice";

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEdittext = (EditText) findViewById(R.id.UsernameEdittext);
        PasswordEditext = (EditText) findViewById(R.id.PasswordEditext);
        SubmitButton=(Button)findViewById(R.id.LoginButton);
        SubmitButton.setOnClickListener(this);

    }
    private Map<String,String> login(){
        username = UsernameEdittext.getText().toString().trim();
        password = PasswordEditext.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_USERNAME, username);
        params.put(Key_PASSWORD, password);
        params.put(Key_CHOICE,"login");
        return params;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LoginButton:
                    Map<String,String> Finalparameters =new HashMap<String,String>();
                    Finalparameters = login();//this is the login call
                    WrapperCustom.getInstance(this).login(Finalparameters);
                     break;
        }
    }
}
