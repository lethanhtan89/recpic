package vn.com.recpic.SettingScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.adapter.BankSMSAdapter;
import vn.com.recpic.SettingScreen.listener.BankSMSListener;
import vn.com.recpic.SettingScreen.model.BankSMS;

/**
 * Created by Administrator on 2/6/2017.
 */

public class BankSMSActivity extends AppCompatActivity implements BankSMSListener {
    private RecyclerView mRecyclerView;
    private ArrayList<BankSMS> bankSMSArrayList = new ArrayList<>();
    private BankSMSAdapter bankSMSAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_sms);
        setupToolbar();
        init();
    }

    private void init(){
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarBank);
        txtToolbar.setText(getResources().getString(R.string.bank_sms));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_bank_sms);
        bankSMSAdapter = new BankSMSAdapter(BankSMSActivity.this, bankSMSArrayList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        //bankSMSAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(bankSMSAdapter);
        showBankSMS();

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.bank_sms_toolbar);
        setSupportActionBar(toolbar);
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
        if(id == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclickItem(int sms_id, int position) {
        BankSMS bankSMS = bankSMSArrayList.get(position);
        Toast.makeText(getApplicationContext(), "" + bankSMS, Toast.LENGTH_SHORT).show();
    }

    private void showBankSMS(){
        BankSMS bankSMS;
        bankSMS = new BankSMS(1,"KBKookmin");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(2, "KEBHana");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(3, "Woori");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(4, "NHNonghyup");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(5, "Citi");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(6, "Hyndai");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(7, "shinhan");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(8, "Samsung");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(9, "Lotte");
        bankSMSArrayList.add(bankSMS);
        bankSMS = new BankSMS(10, "IBKKiup");
        bankSMSArrayList.add(bankSMS);
        bankSMSAdapter.notifyDataSetChanged();
    }
}
