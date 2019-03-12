package remind.edu.myapplication;

import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    TextView tvstarted,tvsubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextInputLayout usernameTextObj = (TextInputLayout)    findViewById(R.id.l1);
        TextInputLayout  mobiletObj = (TextInputLayout)    findViewById(R.id.l2);
        TextInputLayout   educationObj = (TextInputLayout)    findViewById(R.id.l3);
        TextInputLayout   addTextObj = (TextInputLayout)    findViewById(R.id.l4);
        TextInputLayout   emailTextObj = (TextInputLayout)    findViewById(R.id.l5);
        Typeface   hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");

        usernameTextObj .setTypeface(hintfont);
        mobiletObj .setTypeface(hintfont);
        educationObj .setTypeface(hintfont);
        addTextObj .setTypeface(hintfont);
        emailTextObj .setTypeface(hintfont);

        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");




        tvstarted=(TextView)findViewById(R.id.tv_getstarted);
        tvsubmit=(TextView)findViewById(R.id.tv_submit);
        tvstarted.setTypeface(buttonfont);
        tvsubmit.setTypeface(buttonfont);
    }
}
