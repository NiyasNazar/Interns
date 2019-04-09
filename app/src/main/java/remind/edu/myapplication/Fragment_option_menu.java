package remind.edu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import remind.edu.myapplication.Select_edu.Choose_Qualification;

public class Fragment_option_menu extends Fragment {
    TextView choose_course;

    public Fragment_option_menu(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.side_menu_fragment, container, false);
        choose_course=(TextView)rootView.findViewById(R.id.tv_option1);
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
