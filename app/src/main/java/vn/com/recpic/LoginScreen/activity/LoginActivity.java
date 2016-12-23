package vn.com.recpic.LoginScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.recpic.HomeScreen.activity.MainActivity;
import vn.com.recpic.R;
import vn.com.recpic.SignupScreen.activity.ForgotPasswordActivity;
import vn.com.recpic.SignupScreen.activity.SignupActivity;


/**
 * Created by Administrator on 15/12/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private LinearLayout lnLogin, lnSignup;
    private TextView txtForgotPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lnLogin = (LinearLayout) findViewById(R.id.ln_Login);
        lnSignup = (LinearLayout) findViewById(R.id.ln_sign_up);
        txtForgotPassword = (TextView) findViewById(R.id.txt_forgot_password);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
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
}
