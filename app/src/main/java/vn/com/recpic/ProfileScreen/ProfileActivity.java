package vn.com.recpic.ProfileScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import vn.com.recpic.R;

/**
 * Created by Administrator on 19/12/2016.
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
