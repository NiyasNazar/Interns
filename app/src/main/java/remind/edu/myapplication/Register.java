package remind.edu.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    TextView tvstarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        tvstarted=(TextView)findViewById(R.id.tv_getstarted);
        tvstarted.setTypeface(buttonfont);
    }
}
