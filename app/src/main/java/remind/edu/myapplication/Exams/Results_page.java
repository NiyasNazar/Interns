package remind.edu.myapplication.Exams;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import remind.edu.myapplication.Dash_board;
import remind.edu.myapplication.R;

public class Results_page extends AppCompatActivity {
    TextView correctanswer,wronganswer,totalquestions,tvTA,TVCORRECT,TVWRONG,tvresult,totalmarktv,totalmark,tvdone;
String totalmarkfrom,rightanswer,wronganswers,totalq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        totalmarkfrom=getIntent().getStringExtra("totalmark");
        rightanswer=getIntent().getStringExtra("ans");
        wronganswers=getIntent().getStringExtra("wrong");
        totalq=getIntent().getStringExtra("totalquestions");



        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
        correctanswer=(TextView)findViewById(R.id.tvq_RA);
        wronganswer=(TextView)findViewById(R.id.tvq_WA);
        totalquestions=(TextView)findViewById(R.id.tvq_tq);
        tvTA=(TextView)findViewById(R.id.tvq_totalquestions);
        tvTA.setText(totalq);
        TVCORRECT=(TextView)findViewById(R.id.tvq_rightanswers);
        TVCORRECT.setText(rightanswer);
        TVWRONG=(TextView)findViewById(R.id.tvq_wronganswers);
        TVWRONG.setText(wronganswers);
        tvresult=(TextView)findViewById(R.id.tv_result);
        totalmarktv=(TextView)findViewById(R.id.tvq_totalmarktext);
        totalmark=(TextView)findViewById(R.id.tvq_totalmark);
        totalmark.setText(totalmarkfrom);
        tvdone=(TextView)findViewById(R.id.tv_done);
        correctanswer.setTypeface(hintfont);
        wronganswer.setTypeface(hintfont);
        totalquestions.setTypeface(hintfont);
        tvTA.setTypeface(hintfont);
        TVCORRECT.setTypeface(hintfont);
        TVWRONG.setTypeface(hintfont);
        tvresult.setTypeface(hintfont);
        totalmarktv.setTypeface(hintfont);
        tvdone.setTypeface(hintfont);
        totalmark.setTypeface(hintfont);
        tvdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is=new Intent(getApplicationContext(), Dash_board.class);
                startActivity(is);
                finish();
            }
        });


    }
}
