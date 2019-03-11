package remind.edu.myapplication;

import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {

Button button_otp,btn_get_started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextInputLayout   usernameTextObj = (TextInputLayout)    findViewById(R.id.ILusername);
        TextInputLayout   otpTextObj = (TextInputLayout)    findViewById(R.id.ILuserotp);
        Typeface   hintfont = Typeface.createFromAsset(getAssets(), "fonts/Melbourne_reg.otf");
        usernameTextObj .setTypeface(hintfont);
        otpTextObj.setTypeface(hintfont);

        Typeface buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");


        button_otp=(Button)findViewById(R.id.btn_otp);
        btn_get_started=(Button)findViewById(R.id.get_started);
       button_otp.setTypeface(buttonfont);
btn_get_started.setTypeface(buttonfont);
    }
}
