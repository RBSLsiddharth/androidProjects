package com.example.siddharh.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Console;
import java.text.BreakIterator;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//private static final String Register_Url ="http://1c123622.ngrok.io/processing.php";
    Button btnsubmit;
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    Thread thread;
    Handler handler;
    private EditText editText1;
    private EditText editText2;
    public static String RESULT="";
   private BroadcastReceiver myreciever=null;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
             btnsubmit = (Button)findViewById(R.id.bn_submit);
             btnsubmit.setOnClickListener(this);
         editText1 = (EditText)findViewById(R.id.editText3);
         editText2= (EditText)findViewById(R.id.editText4);

    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//THESE ARE THE LIFECYCLE EVENTS OF ACTIVITIES TO CHECK THAT HOW DOES ACTIVTY WORKS
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("I came in start function of main activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("i came in Restart function of main activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("i came in stop method of main activity");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("i came in resume method of main activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("i came in pause method of main activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("i came in destroy method of main activity");
    }
    //THE SUBMIT BUTTON MENTIONS THAT ACTION WHICH WILL BE TAKEN WHEN AN EVENT OF CLICK HAPPENS
    public void submit(){
        final String username = editText1.getText().toString().trim();
        final String password = editText2.getText().toString().trim();
        //INTENT FILTER IS USED, WHOSE ACTION WILL BE TRIGERRED WHEN FOLLOWING ACTIONS TAKES PLACE BY SYSTEM
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
     //BROADCAST RECIEVER IS REGISTERED WHICH WILL WORK ON NETCHANGE
        myreciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info = check.getActiveNetworkInfo();

                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        Toast.makeText(context, "internet connectivity is on", Toast.LENGTH_LONG).show();
                    //RESULT IS USED TO BE ACTING LIKE FLAG
                        MainActivity.RESULT="CONNECTED";
                    } else {
                        Toast.makeText(context, "internet connectivity is  off", Toast.LENGTH_LONG).show();

                    }
                    //SINCE WHEN I TRIED TO RUN THE APPLICATION,IT WAS STOPPING UNFORTUNATELY & TO HANDLE IT I USED EXCEPTION HANDLING AND IT WORKED
                }catch(Exception e){

                    MainActivity.RESULT="DISCONNECTED";
                    Toast.makeText(context,"please connect your internet first dude!!!",Toast.LENGTH_LONG).show();
                }
            }
        };
        super.registerReceiver(myreciever,intentFilter);

        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //THIS IS THE WORK FOR INTERACTING WITH THE API  AND USE OF SERVICES
        Intent intent1 = new Intent(this,LoginService.class);
        /* EditText editText1 = (EditText) findViewById(R.id.editText3);
        username =editText1.getText().toString();
        editText1 = (EditText)findViewById(R.id.editText4);
         password =editText1.getText().toString();*/
        intent1.putExtra("Username",username);
        intent1.putExtra("Password",password);
        startService(intent1);


        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        //THIS IS THE WORK OF THREADS..AND HANDLERS....CURRENTLY WORKING UPON
        thread = new Thread(new Mythread());
        thread.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //body goes here
                ProgressBar progressBar = new ProgressBar(MainActivity.this);
                progressBar.setProgress(msg.arg1);
                progressBar.setVisibility(View.VISIBLE);
            }
        };

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bn_submit:submit();

                System.out.println("THE VALUE OF RESULT IS :"+RESULT);

                break;
        }

    }

}



//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
// THIS WORK DEALS WITH THE DIFFERENT CLASS FOR THREADS IMPLEMENTATION
class Mythread implements Runnable{
    @Override
    public void run() {
        MainActivity m = new MainActivity();
        Message message = Message.obtain();
        for (int i = 0; i < 100; i++) {
            message.arg1 = i ;
                    m.handler.sendMessage(message);
        }
    }
}
