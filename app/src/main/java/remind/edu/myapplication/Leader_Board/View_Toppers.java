package remind.edu.myapplication.Leader_Board;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_Toppers  extends AppCompatActivity {
    TextView subname,toppertv;
    String Examid,title,type;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppers);
        progressDialog=new ProgressDialog(View_Toppers.this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setMessage("Please Wait...");
        recyclerView=(RecyclerView)findViewById(R.id.recv) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Examid=getIntent().getStringExtra("id");
        title=getIntent().getStringExtra("sub");
        type=getIntent().getStringExtra("type");
        Typeface   hintfonts = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Bethanie_Snake_PersonalUseOnly.ttf");

        subname = (TextView) findViewById(R.id.tv_sub);
        subname.setTypeface(hintfonts);
        subname.setText(title);
      toppertv = (TextView) findViewById(R.id.tv_tp);
        toppertv.setTypeface(hintfont);
        Loaddata(Examid,type);




    }

    private void Loaddata(String examid, String type) {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_leaders>call=apiservice.listleaders(examid,type);
        call.enqueue(new Callback<Response_leaders>() {
            @Override
            public void onResponse(Call<Response_leaders> call, Response<Response_leaders> response) {
                Response_leaders response_leaders=response.body();
                List<TopperExam>datalist=response_leaders.getExams();
                Adaptertopper adaptertopper=new Adaptertopper(datalist,getApplicationContext());
                recyclerView.setAdapter(adaptertopper);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Response_leaders> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
