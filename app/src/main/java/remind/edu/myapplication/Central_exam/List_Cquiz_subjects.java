package remind.edu.myapplication.Central_exam;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;


import remind.edu.myapplication.Central_exam.Adapters.Adapter_list_Centralquizsub;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Cquiz_subjects extends AppCompatActivity {
RecyclerView recyclerView;
List<CentralExam>datalist;
TextView mock;
String courseid;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz_subjects);
        courseid=getIntent().getStringExtra("courseid");
        progressDialog=new ProgressDialog(List_Cquiz_subjects.this);
        progressDialog.setMessage("please Wait");
        progressDialog.show();
        recyclerView=(RecyclerView)findViewById(R.id.recv);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Bethanie_Snake_PersonalUseOnly.ttf");
        mock=(TextView)findViewById(R.id.tv_mock_exams);
        mock.setTypeface(hintfont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Loaddata();




    }

    private void Loaddata() {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_Centralqlist>call=apiservice.qlistsx("catb04e85","2");
        call.enqueue(new Callback<Response_Centralqlist>() {
            @Override
            public void onResponse(Call<Response_Centralqlist> call, Response<Response_Centralqlist> response) {
                Log.i("eroor",new Gson().toJson(response.body()));
progressDialog.dismiss();
                Response_Centralqlist response_centralqlist =response.body();
                datalist= response_centralqlist.getExams();
              Adapter_list_Centralquizsub adapter_list_quizsub=new Adapter_list_Centralquizsub(datalist,getApplicationContext());
                recyclerView.setAdapter(adapter_list_quizsub);

            }

            @Override
            public void onFailure(Call<Response_Centralqlist> call, Throwable t) {
                Log.i("eroor",t.getMessage());
                progressDialog.dismiss();

            }
        });


    }
}
