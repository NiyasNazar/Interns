package remind.edu.myapplication;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import remind.edu.myapplication.Web_service.ApiClient;
import remind.edu.myapplication.Web_service.Apiservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView tvstarted,tvsubmit;
    TextInputEditText mName,mMob,mEmail,maddress;
    LinearLayout submitlayout;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(Register.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        TextInputLayout usernameTextObj = (TextInputLayout)    findViewById(R.id.l1);
        TextInputLayout  mobiletObj = (TextInputLayout)    findViewById(R.id.l2);
        TextInputLayout   educationObj = (TextInputLayout)    findViewById(R.id.l3);
        TextInputLayout   addTextObj = (TextInputLayout)    findViewById(R.id.l4);
        TextInputLayout   emailTextObj = (TextInputLayout)    findViewById(R.id.l5);
        mName = (TextInputEditText)    findViewById(R.id.ed_name);
        mMob = (TextInputEditText)    findViewById(R.id.ed_mob);
        mEmail = (TextInputEditText)    findViewById(R.id.ed_email);

        maddress = (TextInputEditText)    findViewById(R.id.ed_address);
submitlayout=(LinearLayout)findViewById(R.id.btn_submit);

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
        submitlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String name=mName.getText().toString();
                String mob=mMob.getText().toString();
                String email=mEmail.getText().toString();
                String address=maddress.getText().toString();
                Register_user(name,mob,email,address);


            }
        });
    }

    private void Register_user(String name, String mob, String email, String address) {
        Apiservice apiservice= ApiClient.getClient().create(Apiservice.class);
        Call<Response_register> call=apiservice.doregister(name,mob,email,address,"","");
        call.enqueue(new Callback<Response_register>() {
            @Override
            public void onResponse(Call<Response_register> call, Response<Response_register> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Response_register> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }


}
