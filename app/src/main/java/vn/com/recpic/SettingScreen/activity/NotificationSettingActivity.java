package vn.com.recpic.SettingScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.adapter.NotificationAdapter;
import vn.com.recpic.SettingScreen.listener.NotificationListener;
import vn.com.recpic.SettingScreen.model.Notification;

/**
 * Created by Administrator on 2/6/2017.
 */

public class NotificationSettingActivity extends AppCompatActivity implements NotificationListener {
    private NotificationAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Notification> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notofication);
        setupToolbar();
        init();
    }

    private void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_notification);
        mAdapter = new NotificationAdapter(this, arrayList, this);
        LinearLayoutManager mLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        mRecyclerView.setAdapter(mAdapter);
        showNotification();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.noti_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarNoti);
        txtToolbar.setText(getResources().getString(R.string.notification));
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
    public void onClickItem(int id, int position) {

    }

    private void showNotification(){
        Notification noti = new Notification(1, "SMS notification");
        arrayList.add(noti);
        noti = new Notification(2, "Receipt notification");
        arrayList.add(noti);
        noti = new Notification(3, "News notification");
        arrayList.add(noti);
        mAdapter.notifyDataSetChanged();
    }
}
