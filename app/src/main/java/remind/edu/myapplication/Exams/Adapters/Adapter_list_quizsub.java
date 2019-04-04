package remind.edu.myapplication.Exams.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import remind.edu.myapplication.Exams.Exam;
import remind.edu.myapplication.Exams.Mock_questions;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Course;
import remind.edu.myapplication.Sub_category_.Sub_category;

public class Adapter_list_quizsub extends RecyclerView.Adapter<Adapter_list_quizsub.MyViewHolder> {

    private List<Exam> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,quizqnumbers,qdetails;
        TextInputLayout titlelayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_quizsubname);
            quizqnumbers=(TextView)view.findViewById(R.id.tv_quizqnum);
            qdetails=(TextView)view.findViewById(R.id.tv_qdetails);

            Typeface hintfont = Typeface.createFromAsset(context.getAssets(), "fonts/Melbourne_reg.otf");
title.setTypeface(hintfont);
quizqnumbers.setTypeface(hintfont);
qdetails.setTypeface(hintfont);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /* int pos=getAdapterPosition();
                    String id=courseList.get(pos).getCourseId();*/
                    Intent is=new Intent(context, Mock_questions.class);
                   // is.putExtra("id",id);
                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);
                }
            });

        }
    }


    public Adapter_list_quizsub(List<Exam> courseList, Context context) {
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
        Exam movie = courseList.get(position);
        holder.title.setText(movie.getTitle() );
        holder.quizqnumbers.setText(movie.getQuestion());


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}