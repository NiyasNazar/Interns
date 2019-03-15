package remind.edu.myapplication.Select_edu;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import remind.edu.myapplication.Login;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Choose_Qualification extends AppCompatActivity {
    TextView selectedu;
    RecyclerView recyclerView;
    Adapter_qualification adapter_qualification;
ProgressDialog pdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__qualification);
        pdialog=new ProgressDialog(Choose_Qualification.this);
        pdialog.setCancelable(false);
        pdialog.show();
        pdialog.setMessage("Please Wait...");
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        selectedu=(TextView)findViewById(R.id.tv_select_edu);
        recyclerView=(RecyclerView) findViewById(R.id.rec_listqualification);
        selectedu.setTypeface(buttonfont);
        Load();

    }

    private void Load() {

        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_course> call=apiservice.course_list();
        call.enqueue(new Callback<Response_course>() {
            @Override
            public void onResponse(Call<Response_course> call, Response<Response_course> response) {
                pdialog.dismiss();
                Response_course response_course=response.body();
                List<Course>datalist=response_course.getCourse();
                adapter_qualification=new Adapter_qualification(datalist,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adapter_qualification);

            }

            @Override
            public void onFailure(Call<Response_course> call, Throwable t) {
                pdialog.dismiss();

            }
        });
    }
}
