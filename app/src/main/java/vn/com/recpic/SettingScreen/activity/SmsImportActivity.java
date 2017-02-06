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

public class SmsImportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_import);
        setupToolbar();
        init();
    }

    private void init(){
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarImport);
        txtToolbar.setText(getResources().getString(R.string.sms_import));
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.import_sms_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_import_sms, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.repeat_sms_action_info:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
