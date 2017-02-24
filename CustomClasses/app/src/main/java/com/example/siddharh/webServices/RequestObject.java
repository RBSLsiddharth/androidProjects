package com.example.siddharh.webServices;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.siddharh.Configuration.LoginActivity;
import com.example.siddharh.Configuration.MyClass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * Created by siddharh on 22/2/17.
 */

public class RequestObject {
    public static Context cont;
    private  RequestQueue requestqueue ;
    Map<String,String> Parameters = new HashMap<String, String>();

    public RequestObject(){
        requestqueue = getRequestQueue();
    }

    public RequestObject(Map<String,String> Params,Context context){
        this.cont = context;
        Parameters = Params;
        WrapperCustom.getInstance(cont).addInQueue(Parameters);
    }
    public RequestQueue getRequestQueue(){
        if (requestqueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestqueue = Volley.newRequestQueue(cont);
        }
        return requestqueue;
    }




    }

