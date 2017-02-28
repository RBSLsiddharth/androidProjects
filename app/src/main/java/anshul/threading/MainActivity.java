package anshul.threading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button handler,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name = getIntent().getExtras().getString("name");
        Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();

        login = (Button)findViewById(R.id.button2);


    }

    @Override
    protected void onResume() {
        super.onResume();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Loginclass.class);
                startActivity(i);
            }
        });
    }
}
