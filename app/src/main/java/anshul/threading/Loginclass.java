package anshul.threading;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Loginclass extends AppCompatActivity implements View.OnClickListener {
    CoordinatorLayout coordinatorLayout;
    ImageView questionImageView;
    Button nextButton;
    EditText nameEditText,codeEditText,phonenoEditText;
    CheckBox policiesCheckBox;
    IntentFilter intentFilter;
    String serverResponse;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            serverResponse = intent.getStringExtra("loginresponse");
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, serverResponse, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginclass);
        questionImageView = (ImageView) findViewById(R.id.question);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        nextButton = (Button) findViewById(R.id.next);
        nameEditText = (EditText) findViewById(R.id.name);
        codeEditText = (EditText) findViewById(R.id.code);
        phonenoEditText = (EditText) findViewById(R.id.phoneno);
        policiesCheckBox= (CheckBox) findViewById(R.id.Policies);
        questionImageView.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        policiesCheckBox.setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter textSentIntentFilter = new IntentFilter("response.LOGIN");
        LocalBroadcastManager
                .getInstance(Loginclass.this)
                .registerReceiver(broadcastReceiver, textSentIntentFilter);
    }



    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(Loginclass.this)
                .unregisterReceiver(broadcastReceiver);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    private void sendd(String name, String code, String phoneno) {
        final String pno = code+"-"+phoneno;
        Map<String,String> map = new HashMap<String,String>();
        map.put(Config.EMAIL,name);
        map.put(Config.PHONENO,pno);
        String url = Config.LOGIN_URL;
        RequestHandler requestHandler = new RequestHandler();
         requestHandler.send(map,url);

    }

    private void send(final String name, String code, final String phoneno) {
        final String pno = code+"-"+phoneno;
        final ProgressDialog pDialog = new ProgressDialog(Loginclass.this);
        pDialog.setMessage(Config.DIALOG);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                if(response.trim().equals(Config.SUCCESS)){
                    Toast.makeText(Loginclass.this,Config.SUCCESS,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Loginclass.this,response,Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Loginclass.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(Config.EMAIL,name);
                map.put(Config.PHONENO,pno);
                return map;
            }

        };
        NetworkManager.getInstance().addToRequestQueue(stringRequest);

    }

    private void next() {
        String name,code,phoneno;
        name = nameEditText.getText().toString();
        code = codeEditText.getText().toString();
        phoneno = phonenoEditText.getText().toString();

        if(name.matches("") || code.matches("") || phoneno.matches("")) {
            Toast.makeText(Loginclass.this,Config.FILL_FIELD,Toast.LENGTH_LONG).show();
        }else {
            sendd(name,code,phoneno);
        }
    }

    private void policies() {
        if(policiesCheckBox.isChecked()) {
            nextButton.setEnabled(true);
        }else {
            nextButton.setEnabled(false);
        }
    }

    private void question() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, Config.QUESTION, Snackbar.LENGTH_LONG);

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
