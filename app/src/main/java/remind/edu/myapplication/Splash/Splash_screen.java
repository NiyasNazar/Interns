package remind.edu.myapplication.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import remind.edu.myapplication.Dash_board;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Choose_Qualification;
import remind.edu.myapplication.Utils.MyPreferences;


import static remind.edu.myapplication.Splash.Login.PREFS_NAME;

public class Splash_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        final boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);






       new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {


if(hasLoggedIn){
    Intent i = new Intent(Splash_screen.this, Dash_board.class);
    startActivity(i);
}else{
    Intent i = new Intent(Splash_screen.this, Login.class);
    startActivity(i);
}





                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
