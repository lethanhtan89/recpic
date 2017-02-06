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

import org.w3c.dom.Text;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.fragment.SMSDialogFragment;

/**
 * Created by Administrator on 2/6/2017.
 */

public class CardSettingActivity extends AppCompatActivity {
    private TextView txtCardSetting, txtBankSMS, txtPaste, txtInbox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_setting);
        setupToolbar();
        init();
    }

    private void init(){
        txtCardSetting = (TextView) findViewById(R.id.txt_card_setting);
        txtBankSMS = (TextView) findViewById(R.id.txt_bank_sms);
        txtPaste = (TextView) findViewById(R.id.txt_paste);
        txtInbox = (TextView) findViewById(R.id.txt_sms_import);

        txtCardSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtBankSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new SMSDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

        txtInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.sms_toolbar);
        setSupportActionBar(toolbar);
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarCard);
        txtToolbar.setText(getResources().getString(R.string.card_sms_setting));
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
