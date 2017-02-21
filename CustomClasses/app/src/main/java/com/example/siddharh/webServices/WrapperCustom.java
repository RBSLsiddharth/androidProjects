package com.example.siddharh.webServices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siddharh.Configuration.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

import static com.android.volley.Request.*;

/**
 * Created by siddharh on 18/2/17.
 */

public class WrapperCustom {
public static Context context;
private static WrapperCustom instance;

    private WrapperCustom() {
    }

    public static WrapperCustom getInstance(Context cons) {

       context= cons;
        if(instance == null)
        {
            instance=new WrapperCustom();
        }
        return instance;

    }

    public Map<String,String> login(final Map<String,String> Parameters) {
        final Map<String,String> UserId= new HashMap<>();

        /*StringRequest jsonObjectRequest = new StringRequest(Method.POST, Register_Url, new Response.Listener<String>() {


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
*/

        return UserId;
    }

    public void addInQueue(Object object){
        final Queue<Object> queue = new LinkedList<Object>();
        Class<?> myobj = object.getClass();
         queue.add(object);
    }

    public void showthequeue(Queue queue){
        Iterator iterator =   queue.iterator();
        while (iterator.hasNext()){
            String element =  iterator.next().toString();
            System.out.println("THE QUEUE HAS FOLLOWING ELEMENT" +element);
        }
    }
    public void redirect(){

          Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

}

