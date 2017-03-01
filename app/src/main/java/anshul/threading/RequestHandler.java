package anshul.threading;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONObject;
import java.util.Map;

/**
 * Request Handler class use to send all request to server.This class contain methods that is used
 * for all GET,POST request.
 */
public class RequestHandler {

    private static Context mContext;

    public RequestHandler(Context context) {
        mContext = context;
    }

    public void send(final Map<String, String> map, String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url
                ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                send(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        NetworkManager.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    private void send(String response) {
        String responseMsg,responseCode;
        try{
            JSONObject jsonObject = new JSONObject(response);
            responseMsg = jsonObject.getString(Config.JSON_RESPONSE_MSG);
            responseCode = jsonObject.getString(Config.JSON_RESPONSE_CODE);
            Intent intent = new Intent(Config.INTENT_LOGIN_ACTION);
            intent.putExtra(Config.M_SERVER_RESPONSE,responseMsg);
            intent.putExtra(Config.M_RESPONSE_CODE,responseCode);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }catch (Exception e){

        }


    }


}
