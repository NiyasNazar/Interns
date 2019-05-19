package remind.edu.myapplication.Central_exam.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import remind.edu.myapplication.Central_exam.CentralExam;
import remind.edu.myapplication.Exams.Exam;
import remind.edu.myapplication.Exams.Mock_questions;
import remind.edu.myapplication.Leader_Board.Central;
import remind.edu.myapplication.R;

public class Adapter_list_Centralquizsub extends RecyclerView.Adapter<Adapter_list_Centralquizsub.MyViewHolder> {

    private List<CentralExam> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,quizqnumbers,qdetails,qnum,qmarks,qtime;
        TextInputLayout titlelayout;
        LinearLayout data;

        public MyViewHolder(View view) {
            super(view);
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
    qnum.setTypeface(hintfont);
            qmarks.setTypeface(hintfont);
            qtime.setTypeface(hintfont);


            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int pos=getAdapterPosition();
                    String id=courseList.get(pos).getExamId();
                    String time=courseList.get(pos).getTime();
                    Intent is=new Intent(context, Mock_questions.class);
                 is.putExtra("id",id);
                 is.putExtra("time",time);
                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);
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
                .inflate(R.layout.itemquizsub, parent, false);

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


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}