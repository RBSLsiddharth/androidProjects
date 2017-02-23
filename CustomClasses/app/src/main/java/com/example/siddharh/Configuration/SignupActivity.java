package com.example.siddharh.Configuration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    public static final String KEY_USERNAME = "Username";
    public static final String Key_PASSWORD = "Password";
    public static final String KEY_REFFERALCODE = "refferalcode";
    private String Key_CHOICE="choice";
    public Map<String, String> params = new HashMap<String, String>();

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

    }
    public SignupActivity(){
        super();
    }
    public SignupActivity(String username,String password,String refferalcode){
        super();
        System.out.println("i came in parameterized constructor");
        params.put(KEY_USERNAME,username);
        params.put(Key_PASSWORD,password);
        params.put(KEY_REFFERALCODE,refferalcode);
        params.put(Key_CHOICE,"login");
    }
    public Map<String,String> submit() {
        username = UsernameEdittext.getText().toString().trim();
        password = PasswordEditext.getText().toString().trim();
        repassword = RepasswordEdittext.getText().toString().trim();
        refferalcode = RefferalCodeEdittext.getText().toString().trim();
        if (repassword.equals(password)) {
            Map<String, String> params = new HashMap<String, String>();
            params.put(KEY_USERNAME, username);
            params.put(Key_PASSWORD, password);
            params.put(KEY_REFFERALCODE, refferalcode);
            params.put(Key_CHOICE,"insert");
            return params;

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
                SignupActivity a = new SignupActivity(username,password,refferalcode);
               /* WrapperCustom.getInstance(this).addInQueue(a);
              */  break;
            case R.id.LoginButton:
                redirect();
                break;
        }
    }

}

