package vn.com.recpic.LaucherScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import vn.com.recpic.LoginScreen.activity.LoginActivity;
import vn.com.recpic.R;


/**
 * Created by Administrator on 14/12/2016.
 */

public class SplashActivity extends AppCompatActivity {
    private static final int POST_DELAYED_TIME = 2000;
    private Runnable mRunnable;
    private Handler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, POST_DELAYED_TIME);
    }
}
