package vn.com.recpic.LoginScreen.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;

import vn.com.recpic.HomeScreen.activity.MainActivity;
import vn.com.recpic.R;
import vn.com.recpic.SignupScreen.activity.ForgotPasswordActivity;
import vn.com.recpic.SignupScreen.activity.SignupActivity;
import vn.com.recpic.Database.MyFunctions;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


/**
 * Created by Administrator on 15/12/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private LinearLayout lnLogin, lnSignup;
    private TextView txtForgotPassword;
    private EditText etEmail, etPassword;
    private static final String TAG_SUCCESS = "success";
    private ProgressDialog progressDialog;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private MyFunctions myFunctions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(getApplicationContext());
        setContentView(R.layout.activity_login);
        myFunctions = new MyFunctions(getApplicationContext());

        if(myFunctions.checkLogin() == true){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        lnLogin = (LinearLayout) findViewById(R.id.ln_Login);
        lnSignup = (LinearLayout) findViewById(R.id.ln_sign_up);
        txtForgotPassword = (TextView) findViewById(R.id.txt_forgot_password);
        etEmail = (EditText) findViewById(R.id.input_email);
        etPassword = (EditText) findViewById(R.id.input_password);
        loginButton = (LoginButton) findViewById(R.id.login_facebook);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("vn.com.recpic", PackageManager.GET_SIGNATURES);
            for(Signature signature: packageInfo.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
            }
        }catch (Exception e){
        }

        loginByFacebook();

        lnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
            }
        });

        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
                new loginProgess().execute();
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loginByFacebook(){
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Login attempt canceled.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Login attempt failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    class loginProgess extends AsyncTask<Void, Void, Integer>{
        String email, password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Loading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            Integer success= new Integer(0);
            try{
                JSONObject jsonObject = myFunctions.userLogin(email, password);
                success = jsonObject.getInt(TAG_SUCCESS);
            }catch (Exception e){
                Log.d("error", "can not login into the Apps "+ e.toString());
            }
            return success;
        }

        @Override
        protected void onPostExecute(Integer success) {
            super.onPostExecute(success);
            if(success == 1){
                progressDialog.dismiss();
                myFunctions.setEmailLogin(email);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Do not login, wrong email or password",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }

    }

    private void setProgressDialog(){

    }

}
