package remind.edu.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import remind.edu.myapplication.Generate_otp.Response_gen_otp;
import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

Button button_otp,btn_get_started;
TextInputEditText mMob,mOtp;
Apiservice apiservice;
ProgressDialog pdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pdialog=new ProgressDialog(Login.this);
        pdialog.setCancelable(false);
        pdialog.setMessage("Please Wait...");
        apiservice= ApiClient.getClient().create(Apiservice.class);
        TextInputLayout   usernameTextObj = (TextInputLayout)    findViewById(R.id.ILusername);
        TextInputLayout   otpTextObj = (TextInputLayout)    findViewById(R.id.ILuserotp);
        mMob=(TextInputEditText)findViewById(R.id.ed_mob);
        mOtp=(TextInputEditText)findViewById(R.id.ed_otp);
        mMob .addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String number = s.toString();
                if(number != null && number.matches("[0-9]{10}")){
pdialog.show();
                    Checknumbervalidation(number);
                }
                //do what you need to do for valid input
     else{

                }



            }
        });




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
        Intent login=new Intent(getApplicationContext(),Dash_board.class);
        startActivity(login);

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

    private void Checknumbervalidation(String number) {
        Call<Response_gen_otp> call=apiservice.generate_Otp(number);
        call.enqueue(new Callback<Response_gen_otp>() {
            @Override
            public void onResponse(Call<Response_gen_otp> call, Response<Response_gen_otp> response) {
                pdialog.dismiss();
                Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_SHORT).show();
                Log.i("response",response.body().getStatus().toString());
            }

            @Override
            public void onFailure(Call<Response_gen_otp> call, Throwable t) {

            }
        });

    }
}
