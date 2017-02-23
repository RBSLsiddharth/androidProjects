package com.example.siddharh.webServices;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.siddharh.Configuration.LoginActivity;
import com.example.siddharh.Configuration.MyClass;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Created by siddharh on 22/2/17.
 */

public class RequestObject extends LoginActivity {
    public static Context cont;
    private  RequestQueue requestqueue ;
    private Context context;
    public Queue queue;
  /*  public RequestObject(Context context) {
        cont=context;
    }*/

    public RequestObject(Context context){
        this.context=context;
        queue = new LinkedList<>();
        queue.add(context);
    }
    public void addinQueue(RequestObject object){
 ;
        requestPost(queue);
    }

    public void requestPost(Queue queue){
   /* RequestQueue reqqueue = WrapperCustom.getInstance(context.getApplicationContext()).getRequestQueue();
  */


    }
    }
