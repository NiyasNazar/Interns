package remind.edu.myapplication.Leader_Board;

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

import remind.edu.myapplication.Exams.Adapters.Adapter_list_quizsub;
import remind.edu.myapplication.Exams.Exam;
import remind.edu.myapplication.Exams.Mock_questions;
import remind.edu.myapplication.R;

public class LeaderAdapterlistsub extends RecyclerView.Adapter<LeaderAdapterlistsub.MyViewHolder> {

    private List<LExam> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,quizqnumbers,qdetails,qnum,qmarks,qtime;
        TextInputLayout titlelayout;
        LinearLayout data;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_quizsubname);
            qdetails=(TextView)view.findViewById(R.id.tv_qdetails);

            data=(LinearLayout) view.findViewById(R.id.lll);

            Typeface hintfont = Typeface.createFromAsset(context.getAssets(), "fonts/Melbourne_reg.otf");
            title.setTypeface(hintfont);
//quizqnumbers.setTypeface(hintfont);
            qdetails.setTypeface(hintfont);



            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    String id=courseList.get(pos).getExamId();
String sub=courseList.get(pos).getTitle();
                    Intent is=new Intent(context, View_Toppers.class);
                    is.putExtra("id",id);
                 is.putExtra("type","1");
                 is.putExtra("sub",sub);
                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);
                }
            });

        }
    }


    public LeaderAdapterlistsub(List<LExam> courseList, Context context) {
        this.courseList = courseList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemleadersub, parent, false);

        return new LeaderAdapterlistsub.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LExam movie = courseList.get(position);
        holder.title.setText(movie.getSubjectName() );
        holder.qdetails.setText(movie.getTitle());


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}