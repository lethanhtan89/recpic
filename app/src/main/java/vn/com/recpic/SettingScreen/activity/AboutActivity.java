package vn.com.recpic.SettingScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.fragment.DeleteUserDialogFragment;
import vn.com.recpic.SettingScreen.fragment.UserGuideDialogFragment;

/**
 * Created by Administrator on 2/8/2017.
 */

public class AboutActivity extends AppCompatActivity {
    private TextView txtUserGuide, txtTerms, txtPrivacy, txtVer, txtDel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupToolbar();
        init();
    }

    private void init(){
        txtUserGuide = (TextView) findViewById(R.id.txt_user_guide);
        txtTerms = (TextView) findViewById(R.id.txt_terms);
        txtPrivacy = (TextView) findViewById(R.id.txt_privacy);
        txtVer = (TextView) findViewById(R.id.txt_ver);
        txtDel = (TextView) findViewById(R.id.txt_del);

        txtUserGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new UserGuideDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

        txtTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
                startActivity(intent);
            }
        });

        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PrivacyActivity.class);
                startActivity(intent);
            }
        });

        txtDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DeleteUserDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarAbout);
        txtToolbar.setText(getResources().getString(R.string.s_about));
        txtToolbar.setAllCaps(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
