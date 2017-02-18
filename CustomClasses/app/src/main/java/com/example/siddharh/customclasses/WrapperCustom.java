package com.example.siddharh.customclasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.*;
import static com.example.siddharh.customclasses.loginactivity.KEY_Username;
import static com.example.siddharh.customclasses.loginactivity.Key_Password;

/**
 * Created by siddharh on 18/2/17.
 */

public class WrapperCustom  {
Context context;


    public WrapperCustom(Context context) {
        this.context = context;
    }

    public Map<String,String> login(final Map<String,String> Parameters) {
        final String Register_Url = "http://192.168.56.1/processing.php";
        final Map<String,String> UserId= new HashMap<>();

        StringRequest jsonObjectRequest = new StringRequest(Method.POST, Register_Url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

              //      Toast.makeText(context,response,Toast.LENGTH_LONG).show();
               try {
                    JSONObject jsonObject = new JSONObject(response);
                   Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                   if(jsonObject.getString("type").equals("insert")){
                      redirect();
                   }else if(jsonObject.getString("message").equals("Userid found")){
                       UserId.put("Userid",jsonObject.getString("userid"));
                   }
                } catch (JSONException e) {
                  Toast.makeText(context,"YOU ARE STUCK UP WITH AN EXCEPTION" , Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {    protected Map<String,String> getParams(){
                  return Parameters;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);

        return UserId;
    }


    public void redirect(){
        Intent intent = new Intent(context,loginactivity.class);
        context.startActivity(intent);
    }

}

