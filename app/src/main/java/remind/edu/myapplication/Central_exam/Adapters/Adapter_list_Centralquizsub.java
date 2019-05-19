package remind.edu.myapplication.Central_exam.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import remind.edu.myapplication.Central_exam.CentralExam;
import remind.edu.myapplication.Central_exam.Central_questions;
import remind.edu.myapplication.Central_exam.Servertime.Response_servertime;
import remind.edu.myapplication.Exams.Exam;
import remind.edu.myapplication.Exams.Mock_questions;
import remind.edu.myapplication.Leader_Board.Central;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  Adapter_list_Centralquizsub extends RecyclerView.Adapter<Adapter_list_Centralquizsub.MyViewHolder> {

    private List<CentralExam> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,quizqnumbers,qdetails,qnum,qmarks,qtime,examdate;
        TextInputLayout titlelayout;
        LinearLayout data;

        public MyViewHolder(View view) {
            super(view);
                    examdate = (TextView) view.findViewById(R.id.tv_examdate);

            title = (TextView) view.findViewById(R.id.tv_quizsubname);
            qdetails=(TextView)view.findViewById(R.id.tv_qdetails);
            qnum=(TextView)view.findViewById(R.id.tv_qnumber);
            qmarks=(TextView)view.findViewById(R.id.tv_marks);
            qtime=(TextView)view.findViewById(R.id.tv_times);
            data=(LinearLayout) view.findViewById(R.id.lll);

            Typeface hintfont = Typeface.createFromAsset(context.getAssets(), "fonts/Melbourne_reg.otf");
title.setTypeface(hintfont);
//quizqnumbers.setTypeface(hintfont);
qdetails.setTypeface(hintfont);
            examdate.setTypeface(hintfont);
    qnum.setTypeface(hintfont);
            qmarks.setTypeface(hintfont);
            qtime.setTypeface(hintfont);


            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int pos=getAdapterPosition();
                    String id=courseList.get(pos).getExamId();
                    String time=courseList.get(pos).getTime();
                    String examstartdate=courseList.get(pos).getDate();
                    Log.i("examdate","ex"+examstartdate);
                    getservertime(examstartdate);
                    /*Intent is=new Intent(context, Central_questions.class);
                 is.putExtra("id",id);
                 is.putExtra("time",time);
                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);*/
                }
            });

        }
    }


    public Adapter_list_Centralquizsub(List<CentralExam> courseList, Context context) {
        this.courseList = courseList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemquizsubcentral, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CentralExam movie = courseList.get(position);
        holder.title.setText(movie.getSubjectName() );
        holder.qdetails.setText(movie.getTitle());
        holder.qmarks.setText(movie.getTotalMark()+" MARKS");
        holder.qnum.setText(movie.getQuestionNo()+" QNS");
        holder.qtime.setText(movie.getTime()+" MINS");
        holder.examdate.setText("Exam On - "+movie.getDate());


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
    public void getservertime(final String examstarddate){
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_servertime> call=apiservice.getservertime();
        call.enqueue(new Callback<Response_servertime>() {
            @Override
            public void onResponse(Call<Response_servertime> call, Response<Response_servertime> response) {
                Response_servertime response_servertime=response.body();
                String currenttime=response_servertime.getTime();
                Log.i("examdate","ex"+currenttime);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date d1 = null;
                Date d2 = null;
                try {
                    Log.i("examdate","execjuting") ;
                    d1 = format.parse(currenttime);
                    d2 = format.parse(examstarddate);

                    //in milliseconds
                    long diff = d2.getTime() - d1.getTime();

                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    Log.i("diffDays" ,""+ diffDays);
                    Log.i("diffHours" , ""+diffHours);
                    Log.i("diffMinutes" , ""+ diffMinutes);
                    Log.i("diffSeconds", ""+  diffSeconds);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_servertime> call, Throwable t) {

            }
        });
    }
}