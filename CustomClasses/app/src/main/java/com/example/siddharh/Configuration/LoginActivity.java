package com.example.siddharh.Configuration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.siddharh.webServices.WrapperCustom;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button SubmitButton;
    Button LoginButton;
    EditText UsernameEdittext;
    EditText PasswordEditext;
    public static final String KEY_USERNAME = "username";
    public static final String Key_PASSWORD = "password";
    public static final String Key_CHOICE = "choice";

    private static final String TAG = "LoginActivity";
    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> params = new HashMap<String, String>();

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

    }
    public LoginActivity(){
        super();
     //   String fix1 = "i came in default constructor";
        System.out.println("i came in default constructor");
        /*Log.d("fix1",fix1);
        Log.d(TAG, "initializeView: width : " + fix1);
   */ }
    public LoginActivity(String username,String password){
      super();
        System.out.println("i came in parameterized constructor");
        // Log.d(TAG, "initializeView: height : " + "sd");

        params.put(KEY_USERNAME,username);
        params.put(Key_PASSWORD,password);
        params.put(Key_CHOICE,"login");


    }
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
                LoginActivity a = new LoginActivity(username,password);
                WrapperCustom.getInstance(this).addInQueue(a);
               // System.out.println("THE INSTANCE OF WRAPPER CLASS IS "+ WrapperCustom.getInstance(this));
                 /* Map<String,String> Finalparameters =new HashMap<String,String>();
                    Finalparameters = login();//this is the login call
                      WrapperCustom.getInstance(this).login(Finalparameters);
*/                                  break;

            case R.id.SubmitButton:
                redirect();
                break;
        }
    }
}
