package remind.edu.myapplication.Exams;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.provider.LoadProvider;

import org.apache.commons.net.io.ToNetASCIIInputStream;

import java.util.List;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mock_questions extends AppCompatActivity {
    List<Question>datalist;
    RadioButton rd1,rd2,rd3,rd4;
    TextView viewquestions,questionnumber;
    int i=0;
    int wrongcount=0;
    int rightanser=0;
    String selectedoption;
LinearLayout submit;
    ProgressDialog progressDialog;
    int totalmark,initialmark=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_questions);
        progressDialog=new ProgressDialog(Mock_questions.this);
        progressDialog.setMessage("please Wait");
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");

        submit=(LinearLayout)findViewById(R.id.llsubmit);
questionnumber=(TextView)findViewById(R.id.view_question_number);
viewquestions=(TextView)findViewById(R.id.view_question);
        questionnumber.setTypeface(hintfont);
        viewquestions.setTypeface(hintfont);

        rd1=(RadioButton)findViewById(R.id.radio_btn1);
        rd2=(RadioButton)findViewById(R.id.radio_btn2);
        rd3=(RadioButton)findViewById(R.id.radio_btn3);
        rd4=(RadioButton)findViewById(R.id.radio_btn4);

rd1.setTypeface(hintfont);
        rd2.setTypeface(hintfont);
        rd3.setTypeface(hintfont);
        rd4.setTypeface(hintfont);

        progressDialog.show();

        Load();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Please Select Answer", Toast.LENGTH_SHORT).show();

                }else {
                    progressDialog.show();

                    int arraysize = datalist.size();
                    String answers=datalist.get(i).getAnswer();
                  int correctmark= Integer.parseInt(datalist.get(i).getMark1());
                   int negativemark= Integer.parseInt(datalist.get(i).getMark2());
                    Log.i("ans",answers);
                    if (selectedoption.equals(answers)){

                        totalmark+=initialmark+correctmark;
                        rightanser++;
                        Log.i("totalmark",""+totalmark);
                        Log.i("rightanswer",""+rightanser);
                    }else{
                        wrongcount++;
                        totalmark+=initialmark+negativemark;
                        Log.i("wrongcount",""+wrongcount);
                        Log.i("netotalmark",""+totalmark);

                    }


                    Toast.makeText(getApplicationContext(), "arraysize" + arraysize, Toast.LENGTH_SHORT).show();
                    if (i < arraysize) {

                        i++;
                        Toast.makeText(getApplicationContext(), "arraysizeinc" + i, Toast.LENGTH_SHORT).show();

                        Load();

                    }
                }

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radio_btn1) {
                    selectedoption="1";


                } else if(checkedId == R.id.radio_btn2) {

                    selectedoption="2";

                } else if(checkedId == R.id.radio_btn3){
                    selectedoption="3";


                } else if(checkedId == R.id.radio_btn4){
                    selectedoption="4";


                }
            }

        });






    }

    private void Load() {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_quiz_questions>call=apiservice.questions();
        call.enqueue(new Callback<Response_quiz_questions>() {
            @Override
            public void onResponse(Call<Response_quiz_questions> call, Response<Response_quiz_questions> response) {
                Response_quiz_questions response_quiz_questions=response.body();
                datalist=response_quiz_questions.getQuestions();
                int listsize=datalist.size();
                if (i<listsize) {
                    viewquestions.setText(datalist.get(i).getQuestions());
                    rd1.setText(datalist.get(i).getOption1());
                    rd2.setText(datalist.get(i).getOption2());
                    rd3.setText(datalist.get(i).getOption3());
                    rd4.setText(datalist.get(i).getOption4());

                    int qn = i + 1;
                    questionnumber.setText(" QUESTION " + qn + "/" + listsize);
                }else{
                    String a= String.valueOf(listsize);
                    String b= String.valueOf(rightanser);
                    String c= String.valueOf(wrongcount);
                    String d= String.valueOf(totalmark);



                    Intent is=new Intent(getApplicationContext(),Results_page.class);
                  is.putExtra("totalquestions",a);
                  is.putExtra("ans",b);
                  is.putExtra("wrong",c);
                  is.putExtra("totalmark",d);
                  startActivity(is);

                }
progressDialog.dismiss();




            }

            @Override
            public void onFailure(Call<Response_quiz_questions> call, Throwable t) {
                progressDialog.dismiss();

            }
        });



    }
}
