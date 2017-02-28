package anshul.threading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread stop = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    move();
                    finish();

                }catch (Exception e){

                }

            }
        };
        stop.start();
    }

    private void move() {
        Intent i = new Intent(Splashscreen.this,Loginclass.class);
        //i.putExtra("name","Anshul laddha");
        startActivity(i);

    }
}
