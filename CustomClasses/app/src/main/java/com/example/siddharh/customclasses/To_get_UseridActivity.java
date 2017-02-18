package com.example.siddharh.customclasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class To_get_UseridActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnsumit;
    EditText editText1;
    public static final String KEY_Username = "username";
    public static final String KEY_Choice = "choice";
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        editText1 = (EditText) findViewById(R.id.editText7);
        btnsumit = (Button) findViewById(R.id.button3);
        btnsumit.setOnClickListener(this);


    }

    private Map<String, String> fetchuserid() {
        username = editText1.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put(KEY_Username, username);
        params.put(KEY_Choice, "fetchuserid");
        return params;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Map<String, String> Finalparameters = new HashMap<String, String>();
                WrapperCustom wrapper = new WrapperCustom(this);
                Finalparameters = wrapper.login(fetchuserid());
                editText1.setText(Finalparameters.get("Userid"));
                break;

        }
    }
}
