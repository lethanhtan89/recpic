package vn.com.recpic.SettingScreen.activity;

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
import vn.com.recpic.SettingScreen.fragment.AddCardDialogFragment;
import vn.com.recpic.SettingScreen.fragment.MyCardDialogFragment;

/**
 * Created by Administrator on 2/7/2017.
 */

public class MyCardSettingActivity extends AppCompatActivity {
    private TextView txtAddNewCard, txtToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card_setting);
        setupToolbar();
        init();
    }

    private void init(){
        txtAddNewCard = (TextView) findViewById(R.id.txt_add_new_card);
        txtAddNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new AddCardDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.card_setting_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        txtToolbar = (TextView) findViewById(R.id.txtToolBarCardSetting);
        txtToolbar.setText(getResources().getString(R.string.card_settings));
        txtToolbar.setAllCaps(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repeat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.repeat_action_info:
                DialogFragment fragment = new MyCardDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
