package com.example.siddharh.Configuration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.siddharh.webServices.RequestObject;
import com.example.siddharh.webServices.WrapperCustom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button SubmitButton;
    Button LoginButton;
    EditText UsernameEdittext;
    EditText PasswordEditext;
    public static final String KEY_USERNAME = "username";
    public static final String Key_PASSWORD = "password";
    public static final String Key_CHOICE = "choice";
   public Map<String,String>  Params = new HashMap<String,String>();
    private static final String TAG = "LoginActivity";

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEdittext = (EditText) findViewById(R.id.UsernameEdittext);
        PasswordEditext = (EditText) findViewById(R.id.PasswordEditext);
        LoginButton=(Button)findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(this);
        SubmitButton = (Button) findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(loginCheck, new IntentFilter("userRegistered"));

    }

    private BroadcastReceiver loginCheck =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            redirect();
        }
    };
        public void redirect(){
        Intent intent =  new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LoginButton:
                username = UsernameEdittext.getText().toString().trim();
                password = PasswordEditext.getText().toString().trim();

                Params.put("username",username);
                Params.put("password",password);
                Params.put("type","login");
                  //   LoginActivity a = new LoginActivity(username,password);
                /*Map<String,String> Finalparameters =new HashMap<String,String>();
                Finalparameters = //this is the login call
                WrapperCustom.getInstance(this).addInQueue(LoginActivity.this);
*/
                RequestObject requestObject = new RequestObject(Params,this);
                // System.out.println("THE INSTANCE OF WRAPPER CLASS IS "+ WrapperCustom.getInstance(this));
                // /*WrapperCustom.getInstance(this).login(Finalparameters);
                // */
                break;

            case R.id.SubmitButton:
                redirect();
                break;
        }
    }
}
