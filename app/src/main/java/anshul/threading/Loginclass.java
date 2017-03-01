package anshul.threading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

/** This is LoginActivity file where user fill his login credential and this file send details to
 * server via RequestHandler class.This file send REST request via RequestHandler.
 */
public class Loginclass extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorLayout mCoordinatorLayout;
    private ImageView mQuestionImageView;
    private Button mNextButton;
    private EditText mNameEditText,mCodeEditText,mPhonenoEditText;
    private CheckBox mPoliciesCheckBox;
    private IntentFilter mIntentFilter;
    private String mServerResponse,mResponseCode;

    //Local broadcast receiver
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mServerResponse = intent.getStringExtra(Config.M_SERVER_RESPONSE);
            mResponseCode = intent.getStringExtra(Config.M_RESPONSE_CODE);
            Snackbar snackbar = Snackbar
                    .make(mCoordinatorLayout, "\""+mServerResponse+"\" : \""
                            +mResponseCode+"\"", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginclass);
        mQuestionImageView = (ImageView) findViewById(R.id.question);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mNextButton = (Button) findViewById(R.id.next);
        mNameEditText = (EditText) findViewById(R.id.name);
        mCodeEditText = (EditText) findViewById(R.id.code);
        mPhonenoEditText = (EditText) findViewById(R.id.phoneno);
        mPoliciesCheckBox = (CheckBox) findViewById(R.id.Policies);
        mQuestionImageView.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPoliciesCheckBox.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Register receiver
        IntentFilter textSentIntentFilter = new IntentFilter(Config.INTENT_LOGIN_ACTION);
        LocalBroadcastManager.getInstance(Loginclass.this)
                .registerReceiver(mBroadcastReceiver, textSentIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Unregister receiver
        LocalBroadcastManager.getInstance(Loginclass.this)
                .unregisterReceiver(mBroadcastReceiver);
    }

    private void send(String name, String code, String phoneno) {
        final String pno = code+"-"+phoneno;
        //HashMap for Logig details
        Map<String,String> map = new HashMap<String,String>();
        map.put(Config.EMAIL,name);
        map.put(Config.PHONENO,pno);
        String url = Config.LOGIN_URL;
        //RequestHandler class object
        RequestHandler requestHandler = new RequestHandler(this.getApplicationContext());
         requestHandler.send(map,url);

    }

    //Next button code
    private void next() {
        String name,code,phoneno;
        name = mNameEditText.getText().toString();
        code = mCodeEditText.getText().toString();
        phoneno = mPhonenoEditText.getText().toString();
        //If else condition to check wheather any field is empty or not
        if(name.matches("") || code.matches("") || phoneno.matches("")) {
            Toast.makeText(Loginclass.this,Config.FILL_FIELD,Toast.LENGTH_LONG).show();
        }else {
            send(name,code,phoneno);
        }
    }

    //Privacy Policy CheckBox code
    private void policies() {
        if(mPoliciesCheckBox.isChecked()) {
            mNextButton.setEnabled(true);
        }else {
            mNextButton.setEnabled(false);
        }
    }

    //Question mark button CheckBox
    private void question() {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, Config.QUESTION, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.question){
            question();
        }
        if (view.getId() == R.id.Policies){
            policies();
        }
        if(view.getId() == R.id.next){
            next();
        }
    }


}
