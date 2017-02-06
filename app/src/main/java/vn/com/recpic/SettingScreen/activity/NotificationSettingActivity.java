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
 * Created by Administrator on 2/6/2017.
 */

public class NotificationSettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notofication);
        setupToolbar();
        init();
    }

    private void init(){
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarNoti);
        txtToolbar.setText(getResources().getString(R.string.notification));
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.noti_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
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
