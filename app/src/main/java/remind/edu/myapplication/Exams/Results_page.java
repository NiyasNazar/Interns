package remind.edu.myapplication.Exams;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import remind.edu.myapplication.Add_mock_result.Response_add_result;
import remind.edu.myapplication.Dash_board;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static remind.edu.myapplication.Splash.Login.PREFS_NAME;

public class Results_page extends AppCompatActivity {
    TextView correctanswer,wronganswer,totalquestions,tvTA,TVCORRECT,TVWRONG,tvresult,totalmarktv,totalmark,tvdone;
String totalmarkfrom,rightanswer,wronganswers,totalq,courseid,examid,userid;
ProgressDialog progressDialog;
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
    SharedPreferences    settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
        userid=settings.getString("userid",null);
  SharedPreferences sharedPreferences=getSharedPreferences("dash",MODE_PRIVATE);
        courseid=sharedPreferences.getString("dash",null);
        totalmarkfrom=getIntent().getStringExtra("totalmark");
        rightanswer=getIntent().getStringExtra("ans");
        examid=getIntent().getStringExtra("id");
        wronganswers=getIntent().getStringExtra("wrong");
        totalq=getIntent().getStringExtra("totalquestions");
progressDialog=new ProgressDialog(Results_page.this);
progressDialog.setMessage("Please Wait ...");
progressDialog.setCancelable(false);
        Toast.makeText(getApplicationContext(),"usr"+userid+"exm"+examid+ "co"+courseid,Toast.LENGTH_LONG).show();

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
               progressDialog.show();
           Add_resultoserver(userid,examid,courseid,totalmarkfrom,rightanswer,wronganswers);

            }
        });


    }

    private void Add_resultoserver(String userid, String examid, String courseid, String totalmarkfrom, String rightanswer, String wronganswers) {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_add_result>call=apiservice.admockres(userid,examid,courseid,totalmarkfrom,rightanswer,wronganswers,"1");
        call.enqueue(new Callback<Response_add_result>() {
            @Override
            public void onResponse(Call<Response_add_result> call, Response<Response_add_result> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"res"+response.body().getStatus(),Toast.LENGTH_SHORT).show();
              /*  Intent is=new Intent(getApplicationContext(), Dash_board.class);
                startActivity(is);
                finish();*/
            }

            @Override
            public void onFailure(Call<Response_add_result> call, Throwable t) {
progressDialog.dismiss();
            }
        });




    }
}
