package com.example.siddharh.Configuration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siddharh.webServices.RequestObject;
import com.example.siddharh.webServices.WrapperCustom;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnsubmit;
    Button btnlogin;
    EditText UsernameEdittext;
    EditText PasswordEditext;
    EditText RepasswordEdittext;
    EditText RefferalCodeEdittext;
    String username="";
    String password="";
    String repassword="";
    String refferalcode="";
  public   Map<String, String> Params = new HashMap<String, String>();

    public static final String KEY_USERNAME = "username";
    public static final String Key_PASSWORD = "password";
    public static final String KEY_REFFERALCODE = "refferalcode";
    private String Key_CHOICE="type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnsubmit = (Button) findViewById(R.id.SubmitButton);
        btnsubmit.setOnClickListener(this);
        UsernameEdittext = (EditText) findViewById(R.id.UsernameEdittext);
        PasswordEditext = (EditText) findViewById(R.id.PasswordEditext);
        RepasswordEdittext = (EditText) findViewById(R.id.RepasswordEdittext);
        RefferalCodeEdittext = (EditText) findViewById(R.id.RefferalCodeEdittext);
        btnlogin = (Button) findViewById(R.id.LoginButton);
        btnlogin.setOnClickListener(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(registrationCheck, new IntentFilter("userRegistered"));

    }
    private BroadcastReceiver registrationCheck =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            redirect();
        }
    };

    public Map<String,String> submit() {
        username = UsernameEdittext.getText().toString().trim();
        password = PasswordEditext.getText().toString().trim();
        repassword = RepasswordEdittext.getText().toString().trim();
        refferalcode = RefferalCodeEdittext.getText().toString().trim();

        if (repassword.equals(password)) {
             Params.put(KEY_USERNAME, username);
             Params.put(Key_PASSWORD, password);
             Params.put(KEY_REFFERALCODE, refferalcode);
             Params.put(Key_CHOICE,"insert");
            return Params;

        }else{
            Toast.makeText(SignupActivity.this,"PASSWORD AND CONFIRM PASSWORD IS NOT SAME",Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void redirect(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubmitButton:
                RequestObject requestObject = new RequestObject(submit(),this);

               /* WrapperCustom.getInstance(this).addInQueue(a);
              */  break;
            case R.id.LoginButton:
                redirect();
                break;
        }
    }

}

