package vn.com.recpic.SignupScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.com.recpic.LoginScreen.activity.LoginActivity;
import vn.com.recpic.R;


/**
 * Created by Administrator on 15/12/2016.
 */

public class SignupActivity extends AppCompatActivity {
    private LinearLayout lnSignup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lnSignup = (LinearLayout) findViewById(R.id.ln_sign_up);
        lnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
            }
        });


    }
}
