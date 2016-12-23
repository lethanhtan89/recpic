package vn.com.recpic.SignupScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import vn.com.recpic.LoginScreen.activity.LoginActivity;
import vn.com.recpic.R;

/**
 * Created by Administrator on 16/12/2016.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private LinearLayout lnSignin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lnSignin = (LinearLayout) findViewById(R.id.ln_forgot_login);
        lnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_close_translate, R.animator.zoom_out);
            }
        });

    }
}
