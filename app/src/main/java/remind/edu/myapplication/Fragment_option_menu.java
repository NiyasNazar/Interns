package remind.edu.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import remind.edu.myapplication.Exams.List_quiz_subjects;
import remind.edu.myapplication.Exams.Mock_questions;
import remind.edu.myapplication.Leader_Board.Leaderboard_Activity;
import remind.edu.myapplication.Select_edu.Choose_Qualification;
import remind.edu.myapplication.Splash.Login;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_option_menu extends Fragment {
    TextView choose_course,logout,startmockexam,Leaderboard;

    public Fragment_option_menu(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.side_menu_fragment, container, false);
     SharedPreferences sharedPreferences=getActivity().getSharedPreferences("dash",MODE_PRIVATE);
       final String course_id=sharedPreferences.getString("dash",null);
        choose_course=(TextView)rootView.findViewById(R.id.tv_option1);
     logout=(TextView)rootView.findViewById(R.id.tv_option3);
        startmockexam=(TextView)rootView.findViewById(R.id.tv_option2);
        Leaderboard=(TextView)rootView.findViewById(R.id.tv_option4);
        Leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is=new Intent(getActivity(), Leaderboard_Activity.class);
                is.putExtra("courseid",course_id);
                startActivity(is);
            }
        });
        startmockexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is=new Intent(getActivity(), List_quiz_subjects.class);
                is.putExtra("courseid",course_id);
                startActivity(is);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(getActivity(), Login.class);
             startActivity(i);
             getActivity().finish();
         }
     });
        choose_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Choose_Qualification.class);
                startActivity(i);

            }
        });
        final Fragment me=this;
        rootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    // Toast.makeText(getActivity(),"OnTouch",Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.exit,R.anim.pop_exit)
                            .remove(me).commit();
                }
                return true;
            }
        });
return rootView;
    }
}
