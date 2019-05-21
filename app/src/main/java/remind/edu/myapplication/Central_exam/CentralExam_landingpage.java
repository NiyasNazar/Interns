package remind.edu.myapplication.Central_exam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import remind.edu.myapplication.Central_exam.Broadcast_package.BroadcastService;
import remind.edu.myapplication.R;

public class CentralExam_landingpage extends AppCompatActivity {
    TextView Caption, Examtime, timleft;
    String id;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_exam_landingpage);
        Caption = (TextView) findViewById(R.id.tv_caption);
        Examtime = (TextView) findViewById(R.id.examstartsin);
        timleft = (TextView) findViewById(R.id.tv_mins);
        id=getIntent().getStringExtra("id");
        time= getIntent().getStringExtra("time");

        startService(new Intent(this, BroadcastService.class));
        Log.i("TAG", "Started service");
        Typeface hintfonts = Typeface.createFromAsset(getAssets(), "fonts/Bethanie_Snake_PersonalUseOnly.ttf");
        Caption.setTypeface(hintfonts);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
        timleft.setTypeface(hintfont);
        Examtime.setTypeface(hintfont);
        new CountDownTimer(120000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timleft.setText("" + String.format("%d min :%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {


                Intent is = new Intent(getApplicationContext(), Central_questions.class);
                is.putExtra("id", id);
                is.putExtra("time", time);
                is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(is);



            }
        }.start();
    }
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i("TAG", "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i("TAG", "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i("TAG", "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            Log.i("TAG", "Countdown seconds remaining: " +  millisUntilFinished / 1000);
            Toast.makeText(this, "hai"+millisUntilFinished, Toast.LENGTH_SHORT).show();
        }
    }
}
