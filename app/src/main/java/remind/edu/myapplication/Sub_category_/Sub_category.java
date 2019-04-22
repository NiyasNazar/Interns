package remind.edu.myapplication.Sub_category_;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import remind.edu.myapplication.Exams.List_quiz_subjects;
import remind.edu.myapplication.Main_category.Adapter_main_cat;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Course;
import remind.edu.myapplication.Select_edu.Response_course;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_category extends AppCompatActivity {
    TextView selectedu;
    RecyclerView recyclerView;
Adapter_sub_cat adapter_main_cat;
    String course_id;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        progressDialog=new ProgressDialog(Sub_category.this);
        progressDialog.setMessage("please Wait");
        progressDialog.show();
        course_id=getIntent().getStringExtra("id");
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        selectedu=(TextView)findViewById(R.id.tv_select_edu);
        recyclerView=(RecyclerView) findViewById(R.id.rec_main_category);
        selectedu.setTypeface(buttonfont);
        Load();
    }
    private void Load() {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_course> call=apiservice.maincatlist(course_id);
        call.enqueue(new Callback<Response_course>() {
            @Override
            public void onResponse(Call<Response_course> call, Response<Response_course> response) {
                Response_course response_course=response.body();

                List<Course> datalist=response_course.getCourse();
                adapter_main_cat=new Adapter_sub_cat(datalist,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter_main_cat);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Response_course> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}
