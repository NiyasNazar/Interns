package remind.edu.myapplication.Select_edu;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import remind.edu.myapplication.R;

public class Choose_Qualification extends AppCompatActivity {
    TextView selectedu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__qualification);
        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        selectedu=(TextView)findViewById(R.id.tv_select_edu);
        selectedu.setTypeface(buttonfont);
    }
}
