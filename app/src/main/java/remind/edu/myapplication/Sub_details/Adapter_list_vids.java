package remind.edu.myapplication.Sub_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import remind.edu.myapplication.Main_category.Main_category;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Adapter_qualification;
import remind.edu.myapplication.Select_edu.Course;

public class Adapter_list_vids extends RecyclerView.Adapter<Adapter_list_vids.MyViewHolder> {

    private List<Vide> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText title;
        TextInputLayout titlelayout;

        public MyViewHolder(View view) {
            super(view);
            title = (EditText) view.findViewById(R.id.ed_qualification_nanme);
            title.setFocusable(false);
            Typeface hintfont = Typeface.createFromAsset(context.getAssets(), "fonts/Melbourne_reg.otf");
            title.setTypeface(hintfont);
            titlelayout = (TextInputLayout) view.findViewById(R.id.l4);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  int pos=getAdapterPosition();
                  //  String id=courseList.get(pos).getCourseId();
                    Intent is=new Intent(context, Video_Views.class);
                   // is.putExtra("id",id);
                    context.startActivity(is);
                }
            });

        }
    }


    public Adapter_list_vids( List<Vide> courseList,Context context) {
        this.courseList = courseList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_sub, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vide movie = courseList.get(position);
        holder.title.setText(movie.getTitle());


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}