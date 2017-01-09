package vn.com.recpic.SignupScreen.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONObject;

import vn.com.recpic.LoginScreen.activity.LoginActivity;
import vn.com.recpic.R;
import vn.com.recpic.database.JSONParser;
import vn.com.recpic.database.MyFunctions;


/**
 * Created by Administrator on 15/12/2016.
 */

public class SignupActivity extends AppCompatActivity {
    public static final String TAG_SUCCESS = "success";
    private LinearLayout lnSignup;
    private EditText etEmail, etPassword, etConfirm;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
    }

    private void init(){
        etEmail = (EditText) findViewById(R.id.input_email);
        etPassword = (EditText) findViewById(R.id.input_password);
        etConfirm = (EditText) findViewById(R.id.input_confirm_password);

        lnSignup = (LinearLayout) findViewById(R.id.ln_sign_up);
        lnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new signupProgess().execute();
            }
        });
    }

    class signupProgess extends AsyncTask<Void, Void, String>{
        String email, password, confirm;
        MyFunctions myFunctions;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(SignupActivity.this);
            progressDialog.setMessage("Loading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            confirm = etConfirm.getText().toString();
            String success = null;
            if(email.equals(null) || password.equals(null) || confirm.equals(null)){
                Toast.makeText(getApplicationContext(), "Please enter eonough the information", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    myFunctions = new MyFunctions(getApplicationContext());
                    JSONObject jsonObject = myFunctions.userRegister(email, password, confirm);
                    success = jsonObject.getString(TAG_SUCCESS);
                } catch (Exception e) {
                }
            }
            return success;
        }

        @Override
        protected void onPostExecute(String success) {
            super.onPostExecute(success);
            if(Integer.parseInt(success) == 1){
                Toast.makeText(getApplicationContext(), "Sign up success with email: " + email, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Email is  exists", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
    }
}
