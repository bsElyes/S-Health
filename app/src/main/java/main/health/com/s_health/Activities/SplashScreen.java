package main.health.com.s_health.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;

import main.health.com.s_health.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Parse.initialize(this, "zUVQ5slWSni1eBn4nD9l9Bw0tUR60CPvGR00ZTnD", "lRNjszVFKGiSmOn8wESiroVtFaG11PGBtGYF5DwR");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, SIntro.class);
                startActivity(i);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        }, 5000);




    }



}
