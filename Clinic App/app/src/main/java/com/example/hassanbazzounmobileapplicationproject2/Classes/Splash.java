package com.example.hassanbazzounmobileapplicationproject2.Classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hassanbazzounmobileapplicationproject2.LoginMain;
import com.example.hassanbazzounmobileapplicationproject2.R;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the LoginMain */
                Intent mainIntent = new Intent(Splash.this, LoginMain.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
