package remind.edu.myapplication.Course_List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import remind.edu.myapplication.Main_category.Main_category;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Select_edu.Course;
import remind.edu.myapplication.Sub_details.Subject_details;

public class Adapter_sublist extends RecyclerView.Adapter<Adapter_sublist.MyViewHolder> {

    private List<Subjects> courseList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView subimg;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sub_names);
            subimg=(ImageView)view.findViewById(R.id.imv_subs);
            Typeface hintfont = Typeface.createFromAsset(context.getAssets(), "fonts/Melbourne_reg.otf");
            title.setTypeface(hintfont);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  int pos=getAdapterPosition();
                    String subname=courseList.get(pos).getSubjectName();
                    String Image_url=courseList.get(pos).getImage();
                    Intent is=new Intent(context, Subject_details.class);
                    is.putExtra("subname",subname);
                    is.putExtra("Image_url",Image_url);
                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);
                }
            });

        }
    }


    public Adapter_sublist(List<Subjects> courseList, Context context) {
        this.courseList = courseList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsub_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Subjects movie = courseList.get(position);
       holder.title.setText(movie.getSubjectName());
       String img_url=movie.getImage();
        Glide.with(context).load(img_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.subimg);

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}