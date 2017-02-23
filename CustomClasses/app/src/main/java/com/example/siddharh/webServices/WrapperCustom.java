package com.example.siddharh.webServices;

import android.content.Context;
import android.content.Intent;
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
        requestqueue = getRequestQueue();
      /*  queue =  new LinkedList<>();*/
    }

    public static synchronized WrapperCustom getInstance(Context cons) {

        if(instance == null)
        {
            instance=new WrapperCustom(cons);
        }
        return instance;

    }

    public RequestQueue getRequestQueue(){
        if (requestqueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestqueue = Volley.newRequestQueue(cont.getApplicationContext());
        }
        return requestqueue;
    }

    /*public Map<String,String> login(final Map<String,String> Parameters) {
        final Map<String,String> UserId= new HashMap<>();

        *//**//*StringRequest jsonObjectRequest = new StringRequest(Method.POST, Register_Url, new Response.Listener<String>() {


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


        return UserId;
    }
*/



    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }




    public  void addInQueue(Request<Object> object){
        String Register_Url="http://192.168.56.1/processing.php";
         final Map<String,String> UserId= new HashMap<>();

        final StringRequest jsonObjectRequest = new StringRequest(Method.POST, Register_Url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
try{
            JSONObject jsonObject = new JSONObject(response);
                //      Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    Toast.makeText(cont,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    if(jsonObject.getString("type").equals("insert")){
                        redirect();
                    }else if(jsonObject.getString("message").equals("Userid found")){
                        UserId.put("Userid",jsonObject.getString("userid"));
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
        }){

        };

        getRequestQueue().add(object);

    }



    //is for returning the element of a queue
    public Object fetchtheelement(Queue queue){
        Iterator iterator =   queue.iterator();
        return iterator.next();
    }

    //is the redirect function for redirecting after the work is done
    public void redirect(){

        Intent intent = new Intent(cont,LoginActivity.class);
        cont.startActivity(intent);
    }

}

