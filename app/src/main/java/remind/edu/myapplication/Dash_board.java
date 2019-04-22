package remind.edu.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.widget.EmptyRecyclerView;

import java.util.List;

import remind.edu.myapplication.Course_List.Adapter_sublist;
import remind.edu.myapplication.Course_List.Response_sublist;

import remind.edu.myapplication.Course_List.Subjects;
import remind.edu.myapplication.Exams.List_quiz_subjects;
import remind.edu.myapplication.Sub_category_.Sub_category;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dash_board extends AppCompatActivity {
    TextView Economics,Geography,Chemistry,Maths,Bilology,English,Subjectss,Mock;
RecyclerView recyclerView;
Button mockstart;
ProgressDialog progressDialog;
Fragment_option_menu fragment_option_menu;
ImageView side;
String course_id;
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        fragment_option_menu=new Fragment_option_menu();
      //  course_id=getIntent().getStringExtra("id");
        sharedPreferences=getSharedPreferences("dash",MODE_PRIVATE);
        course_id=sharedPreferences.getString("dash",null);
        Toast.makeText(getApplicationContext(),"xx"+course_id,Toast.LENGTH_LONG).show();

        side=(ImageView)findViewById(R.id.side_menu);
        side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(fragment_option_menu);
            }
        });
        progressDialog=new ProgressDialog(Dash_board.this);
        progressDialog.setMessage("please Wait");
        progressDialog.show();
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
recyclerView=(RecyclerView)findViewById(R.id.recv_sublist);
recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        Subjectss=(TextView)findViewById(R.id.tv_Subjects);
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        Subjectss.setTypeface(buttonfont);
        Mock=(TextView)findViewById(R.id.tv_mock);
        mockstart=(Button)findViewById(R.id.btn_mock_start);
        mockstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is=new Intent(getApplicationContext(), List_quiz_subjects.class);
                is.putExtra("courseid",course_id);
                startActivity(is);
            }
        });
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_sublist>call=apiservice.sublist(course_id);
        call.enqueue(new Callback<Response_sublist>() {
            @Override
            public void onResponse(Call<Response_sublist> call, Response<Response_sublist> response) {
                Response_sublist response_sublist=response.body();
                List<Subjects>datalist=response_sublist.getSubject();
                Adapter_sublist adapter_sublist=new Adapter_sublist(datalist,getApplicationContext());
                recyclerView.setAdapter(adapter_sublist);
progressDialog.dismiss();


            }

            @Override
            public void onFailure(Call<Response_sublist> call, Throwable t) {
                progressDialog.dismiss();


            }
        });


    }
    private void replace(Fragment fragment) {


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction .setCustomAnimations(R.anim.sss,R.anim.slide_up);
        fragmentTransaction.replace(R.id.contain, fragment);
        fragmentTransaction.commit();


    }
}
