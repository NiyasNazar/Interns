package remind.edu.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Dash_board extends AppCompatActivity {
    TextView Economics,Geography,Chemistry,Maths,Bilology,English,Subjects,Mock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
        Geography=(TextView)findViewById(R.id.tv_Geography);
        Economics=(TextView)findViewById(R.id.tv_Economics);
        Chemistry=(TextView)findViewById(R.id.tv_Chemistry);
        Maths=(TextView)findViewById(R.id.tv_Maths);
        Bilology=(TextView)findViewById(R.id.tv_Biology);
        English=(TextView)findViewById(R.id.tv_English);
        Subjects=(TextView)findViewById(R.id.tv_Subjects);
        Mock=(TextView)findViewById(R.id.tv_mock);
Geography.setTypeface(hintfont);
        Economics.setTypeface(hintfont);

        Chemistry.setTypeface(hintfont);

        Maths.setTypeface(hintfont);

        Bilology.setTypeface(hintfont);

        English.setTypeface(hintfont);
        Subjects.setTypeface(hintfont);


    }
}
