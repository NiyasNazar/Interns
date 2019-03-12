package remind.edu.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


button_otp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
btn_get_started.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent get_started=new Intent(getApplicationContext(),Register.class);
        startActivity(get_started);

    }
});



    }
}
