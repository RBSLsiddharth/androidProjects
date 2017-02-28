package anshul.threading;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 27/2/17.
 */
public class RequestHandler {


    private static Context context;

    public static Context getContext() {
        return context;
    }

    public void send(final Map<String, String> map, String url) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals(Config.SUCCESS)){

                send("Successfully Loged In");

                }else{
                    send("Please check your email and phoneno");
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(RequestHandler.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }

        };
        NetworkManager.getInstance().addToRequestQueue(stringRequest);


    }

    private void send(String response) {
        Intent intent = new Intent("response.LOGIN");
        intent.putExtra("loginresponse",response);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }


}
