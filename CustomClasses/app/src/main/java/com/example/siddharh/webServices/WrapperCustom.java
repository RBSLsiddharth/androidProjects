package com.example.siddharh.webServices;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siddharh.Configuration.LoginActivity;
import com.example.siddharh.Configuration.MyClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import static com.android.volley.Request.*;

/**
 * Created by siddharh on 18/2/17.
 */

public class WrapperCustom {
public static Context cont;
private static WrapperCustom instance;
    private  RequestQueue requestqueue ;
    Queue queue ;

    private WrapperCustom(Context context) {
    cont=context;
       /*  queue =  new LinkedList<>();*/
    }

    public static synchronized WrapperCustom getInstance(Context cons) {

        if(instance == null)
        {
            instance=new WrapperCustom(cons);
        }
        return instance;

    }


    public  void addInQueue(final Map<String,String> parameters){
        String Register_Url="http://192.168.56.1/processing.php";

        final StringRequest stringRequest = new StringRequest(Method.POST, Register_Url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
try{
            JSONObject jsonObject = new JSONObject(response);
                //      Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    Toast.makeText(cont,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    if(jsonObject.getString("type").equals("insert")){
                        Intent intent =  new Intent("userRegistered");
                        LocalBroadcastManager.getInstance(cont).sendBroadcast(intent);
                    }else if(jsonObject.getString("type").equals("login")){
                        Intent intent =  new Intent("loggedin");
                        LocalBroadcastManager.getInstance(cont).sendBroadcast(intent);
                    }
                } catch (JSONException e) {
                    Toast.makeText(cont,"YOU ARE STUCK UP WITH AN EXCEPTION" , Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(cont,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){ protected Map<String,String> getParams(){
             return parameters;
        }

        };
             RequestObject requestObject = new RequestObject();
             requestObject.getRequestQueue().add(stringRequest);
    }



}

