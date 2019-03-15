package remind.edu.myapplication.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import remind.edu.myapplication.Login;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Choose_Qualification;
import remind.edu.myapplication.Utils.MyPreferences;

public class Splash_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {



                    Intent i = new Intent(Splash_screen.this, Login.class);
                    startActivity(i);




                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
