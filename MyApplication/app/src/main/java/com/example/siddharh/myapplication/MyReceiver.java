package com.example.siddharh.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static java.lang.System.out;


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//THIS BROADCAST RECIEVER WAS USED BEFORE ,WHEN I WAS LAUNCHING BROADCAST RECIEVER ON THE BUTTON CLICK EVENT.....BUT NOW SINCE WE ARE CREATING ANONYMOUSLY ON MAINACTIVITY ..THUS IT IS NOT LINKED ANYMORE
//
//
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

public class MyReceiver extends BroadcastReceiver{

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Toast.makeText(context, "internet connectivity is not on",Toast.LENGTH_LONG).show();
        try {
            ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = check.getActiveNetworkInfo();

            if (info.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(context, "internet connectivity is on", Toast.LENGTH_LONG).show();
                MainActivity.RESULT="CONNECTED";
                //      R.id.bn_submit.enable;
            } else {
                Toast.makeText(context, "internet connectivity is  off", Toast.LENGTH_LONG).show();

            }

        }catch(Exception e){

            MainActivity.RESULT="DISCONNECTED";
            Toast.makeText(context,"please connect your internet first dude!!!",Toast.LENGTH_LONG).show();
        }
    }
/*

        throw new UnsupportedOperationException("Not yet implemented");
*/


    }


