package com.example.siddharh.customclasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class loginactivity extends AppCompatActivity implements View.OnClickListener {
    Button btnsumit;
    EditText editText1;
    EditText editText2;
    public static final String KEY_Username = "username";
    public static final String Key_Password = "password";
    public static final String Key_Choice = "choice";

    private String username = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        btnsumit=(Button)findViewById(R.id.button);
        btnsumit.setOnClickListener(this);

    }
    private Map<String,String> login(){
        username = editText1.getText().toString().trim();
        password = editText2.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_Username, username);
        params.put(Key_Password, password);
        params.put(Key_Choice,"login");
        return params;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                    Map<String,String> Finalparameters =new HashMap<String,String>();
                    Finalparameters = login();
                    WrapperCustom wrapper = new WrapperCustom(this);
                    wrapper.login(Finalparameters);
                     break;
        }
    }
}
