package vn.com.recpic.SettingScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import vn.com.recpic.R;

/**
 * Created by Administrator on 2/8/2017.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupToolbar();
        init();
    }

    private void init(){

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
