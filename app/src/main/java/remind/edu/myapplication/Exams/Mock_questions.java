package remind.edu.myapplication.Exams;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.provider.LoadProvider;
import com.squareup.picasso.Picasso;

import org.apache.commons.net.io.ToNetASCIIInputStream;

import java.util.List;
import java.util.concurrent.TimeUnit;

import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mock_questions extends AppCompatActivity  {
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
    String id;
    String url;
    TextView _tv;
Button imagebutton;
 RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_questions);
        imagebutton=(Button)findViewById(R.id.viewimage);
       _tv = (TextView) findViewById( R.id.view_timer );
        id=getIntent().getStringExtra("id");
        Log.i("idxx",id);
        progressDialog=new ProgressDialog(Mock_questions.this);
        progressDialog.setMessage("please Wait");
        radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
_tv.setTypeface(hintfont);
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
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                    ImageView imv = (ImageView) alertLayout.findViewById(R.id.imvs);
                Picasso.with(getApplicationContext()).load(url).into(imv);
                Log.i("urlempty",url);


                    AlertDialog.Builder alert = new AlertDialog.Builder(Mock_questions.this);
                    alert.setTitle("");
                    // this is set the view from XML inside AlertDialog
                    alert.setView(alertLayout);
                    // disallow cancel of AlertDialog on click of back button and outside touch
                    alert.setCancelable(false);
                    alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = alert.create();
                    dialog.show();


                }


        });

        Load();
        new CountDownTimer(5400000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                _tv.setText(""+String.format("%d min :%d sec",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {

            }
        }.start();

       // if (!url.equals("")){


       // }
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



                    if (i < arraysize) {

                        i++;


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

radioGroup.clearCheck();

        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_quiz_questions>call=apiservice.questions("exm_20122");
        call.enqueue(new Callback<Response_quiz_questions>() {
            @Override
            public void onResponse(Call<Response_quiz_questions> call, Response<Response_quiz_questions> response) {
                Response_quiz_questions response_quiz_questions=response.body();
                datalist=response_quiz_questions.getQuestions();
                final int listsize=datalist.size();
               

                if (i<listsize) {


                    viewquestions.setText(datalist.get(i).getQuestions());
                    rd1.setText(datalist.get(i).getOption1());
                    rd2.setText(datalist.get(i).getOption2());
                    rd3.setText(datalist.get(i).getOption3());
                    rd4.setText(datalist.get(i).getOption4());
                    url=datalist.get(i).getImage();
                   if (url.isEmpty()){
                       Log.i("urlempty",url);

                   }

                    if (!url.equals("")){
                        imagebutton.setVisibility(View.VISIBLE);
                    }else {
                        imagebutton.setVisibility(View.GONE);

                    }
                    Log.i("imageurl",url);
                    int qn = i + 1;
                    questionnumber.setText(" QUESTION " + qn + "/" + listsize);
                }else{
                  
                    String a= String.valueOf(listsize);
                    String b= String.valueOf(rightanser);
                    String c= String.valueOf(wrongcount);
                    String d= String.valueOf(totalmark);
                    finishac(a,b,c,d);




                }
progressDialog.dismiss();




            }

            @Override
            public void onFailure(Call<Response_quiz_questions> call, Throwable t) {
                progressDialog.dismiss();

            }
        });



    }

    private void finishac(String a, String b, String c, String d) {
        Intent is=new Intent(getApplicationContext(),Results_page.class);
        is.putExtra("totalquestions",a);
        is.putExtra("ans",b);
        is.putExtra("wrong",c);
        is.putExtra("totalmark",d);
        startActivity(is);
    }
}
