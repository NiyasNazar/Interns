package remind.edu.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nbsp.materialfilepicker.widget.EmptyRecyclerView;

import java.util.List;

import remind.edu.myapplication.Course_List.Adapter_sublist;
import remind.edu.myapplication.Course_List.Response_sublist;

import remind.edu.myapplication.Course_List.Subjects;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dash_board extends AppCompatActivity {
    TextView Economics,Geography,Chemistry,Maths,Bilology,English,Subjectss,Mock;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
recyclerView=(RecyclerView)findViewById(R.id.recv_sublist);
recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        Subjectss=(TextView)findViewById(R.id.tv_Subjects);
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Subjectss.setTypeface(buttonfont);
        Mock=(TextView)findViewById(R.id.tv_mock);
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_sublist>call=apiservice.sublist("cat304216");
        call.enqueue(new Callback<Response_sublist>() {
            @Override
            public void onResponse(Call<Response_sublist> call, Response<Response_sublist> response) {
                Response_sublist response_sublist=response.body();
                List<Subjects>datalist=response_sublist.getSubject();
                Adapter_sublist adapter_sublist=new Adapter_sublist(datalist,getApplicationContext());
                recyclerView.setAdapter(adapter_sublist);



            }

            @Override
            public void onFailure(Call<Response_sublist> call, Throwable t) {

            }
        });


    }
}
