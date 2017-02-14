package com.example.siddharh.myapplication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SignupService extends IntentService {
    private EditText editText1;
    private EditText editText2;
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.siddharh.myapplication.action.FOO";
    private static final String ACTION_BAZ = "com.example.siddharh.myapplication.action.BAZ";
    public static final String KEY_FirstName="firstname";
    public static final String Key_lastname="lastname";
    public static final String KEY_Email="email";
    public static final String KEY_Contact="contact";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.siddharh.myapplication.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.siddharh.myapplication.extra.PARAM2";

    public SignupService() {
        super("SignupService");

    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SignupService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SignupService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("I CAME IN SEVICE");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
            final String firstname = intent.getStringExtra("firstname");
            final String lastname = intent.getStringExtra("lastname");
            final String  email = intent.getStringExtra("email");
            final String contact = intent.getStringExtra("contact");

            final String Register_Url ="http://1c123622.ngrok.io/processing.php";

            StringRequest stringRequest= new StringRequest(Request.Method.POST, Register_Url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(SignupService.this, response, Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.toString());
                    Toast.makeText(SignupService.this,error.toString(),Toast.LENGTH_LONG).show();
                }
            }){
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(KEY_FirstName,firstname);
                    params.put(Key_lastname,lastname);
                    params.put(KEY_Email,email);
                    params.put(KEY_Contact,contact);
                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
