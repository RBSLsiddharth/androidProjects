package com.example.siddharh.customclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class signupactivity extends AppCompatActivity implements View.OnClickListener {
    Button btnsubmit;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    String username="";
    String password="";
    String repassword="";
    String refferalcode="";
    public static final String KEY_Username = "Username";
    public static final String Key_Password = "Password";
    public static final String KEY_Refferalcode = "refferalcode";
    private String KEY_Choice="choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactiviy);
        btnsubmit = (Button) findViewById(R.id.button2);
        btnsubmit.setOnClickListener(this);
        editText1 = (EditText) findViewById(R.id.editText4);
        editText2 = (EditText) findViewById(R.id.editText5);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText6);
    }
    public Map<String,String> submit() {
        username = editText1.getText().toString().trim();
        password = editText2.getText().toString().trim();
        repassword = editText3.getText().toString().trim();
        refferalcode = editText4.getText().toString().trim();
        if (repassword.equals(password)) {
            Map<String, String> params = new HashMap<String, String>();
            params.put(KEY_Username, username);
            params.put(Key_Password, password);
            params.put(KEY_Refferalcode, refferalcode);
            params.put(KEY_Choice,"insert");
            return params;

        }else{
            Toast.makeText(signupactivity.this,"PASSWORD AND CONFIRM PASSWORD IS NOT SAME",Toast.LENGTH_LONG).show();
            return null;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                 WrapperCustom wrapper = new WrapperCustom(this);
                wrapper.login(submit());

                break;

        }
    }

}
