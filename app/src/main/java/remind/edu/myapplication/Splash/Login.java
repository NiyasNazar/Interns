package remind.edu.myapplication.Splash;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import remind.edu.myapplication.Dash_board;
import remind.edu.myapplication.Generate_otp.Response_gen_otp;
import remind.edu.myapplication.Generate_otp.Response_validate_otp;
import remind.edu.myapplication.R;
import remind.edu.myapplication.Register;
import remind.edu.myapplication.Select_edu.Choose_Qualification;
import remind.edu.myapplication.Splash.Splash_screen;
import remind.edu.myapplication.Utils.MyPreferences;
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
    boolean isFirstTime;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = getSharedPreferences(PREFS_NAME, 0); // 0 - for private mode
        editor = settings.edit();
        isFirstTime = MyPreferences.isFirst(Login.this);
        pdialog=new ProgressDialog(Login.this);
        pdialog.setCancelable(false);
        pdialog.setMessage("Please Wait...");
        apiservice= ApiClient.getClient().create(Apiservice.class);
        TextInputLayout   usernameTextObj = (TextInputLayout)    findViewById(R.id.ILusername);
        TextInputLayout   otpTextObj = (TextInputLayout)    findViewById(R.id.ILuserotp);
        mMob=(TextInputEditText)findViewById(R.id.ed_mob);
        mOtp=(TextInputEditText)findViewById(R.id.ed_otp);
        mOtp .addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) {
                String number = mMob.getText().toString();
                String otp = mOtp.getText().toString();

                if(!number.equals("")&&number != null && number.matches("[0-9]{10}")&& otp.matches("[0-9]{4}")){
                    pdialog.show();
                    Validate_otp(number,otp);
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
                pdialog.show();
                String mobilenum=mMob.getText().toString();
                String otp=mOtp.getText().toString();
                Checknumbervalidation(mobilenum);
                // Validate_otp(mobilenum,otp);
                // Intent login=new Intent(getApplicationContext(),Dash_board.class);
                //startActivity(login);

            }
        });
        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent get_started=new Intent(getApplicationContext(), Register.class);
                startActivity(get_started);

            }
        });



    }

    private void Validate_otp(String mobile,String otp) {
        Call<Response_validate_otp>call=apiservice.verify_otp(mobile,otp);
        call.enqueue(new Callback<Response_validate_otp>() {
            @Override
            public void onResponse(Call<Response_validate_otp> call, Response<Response_validate_otp> response) {
                Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_SHORT).show();
                pdialog.dismiss();
                if (response.body().getStatus().equals("success")) {
                    editor.putBoolean("hasLoggedIn", true);

// Commit the edits!
                    editor.commit();
                    mMob.setText("");
                    mOtp.setText("");
                    if (isFirstTime){
                        Intent i = new Intent(Login.this, Choose_Qualification.class);
                        startActivity(i);
                    }else {
                        Intent login = new Intent(getApplicationContext(), Dash_board.class);
                        startActivity(login);
                    }
                }else {

                    mOtp.setError("OTP Mismatch");

                }
            }

            @Override
            public void onFailure(Call<Response_validate_otp> call, Throwable t) {

            }
        });
    }

    private void Checknumbervalidation(String number) {
        Call<Response_gen_otp> call=apiservice.generate_Otp(number);
        call.enqueue(new Callback<Response_gen_otp>() {
            @Override
            public void onResponse(Call<Response_gen_otp> call, Response<Response_gen_otp> response) {
                pdialog.dismiss();
                String responsemob=response.body().getStatus();
                if (responsemob.equals("otp_send")) {
                    Toast.makeText(getApplicationContext(), "OTP Sent to Your Registered Mobile Number" , Toast.LENGTH_SHORT).show();
                    button_otp.setText("RESEND OTP");
                }else{
                    Toast.makeText(getApplicationContext(), "Mobile Number Not Registered" , Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Response_gen_otp> call, Throwable t) {

            }
        });

    }
}