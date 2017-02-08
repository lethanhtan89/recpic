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
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.adapter.FAQAdapter;
import vn.com.recpic.SettingScreen.listener.FAQListener;
import vn.com.recpic.SettingScreen.model.FAQ;

/**
 * Created by Administrator on 2/8/2017.
 */

public class FAQActivity extends AppCompatActivity implements FAQListener{
    private ArrayList<FAQ> arrayList = new ArrayList<>();
    private FAQAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        setupToolbar();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_faq);
        mAdapter = new FAQAdapter(this, arrayList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        mRecyclerView.setAdapter(mAdapter);
        showFAQ();
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.faq_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolBarFaq);
        txtToolbar.setText(getResources().getString(R.string.s_faq));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void showFAQ(){
        FAQ faq;
        faq = new FAQ(1, getResources().getString(R.string.faq_1));
        arrayList.add(faq);
        faq = new FAQ(2, getResources().getString(R.string.faq_2));
        arrayList.add(faq);
        faq = new FAQ(3, getResources().getString(R.string.faq_3));
        arrayList.add(faq);
        faq = new FAQ(4, getResources().getString(R.string.faq_4));
        arrayList.add(faq);
        faq = new FAQ(5, getResources().getString(R.string.faq_5));
        arrayList.add(faq);
        faq = new FAQ(6, getResources().getString(R.string.faq_6));
        arrayList.add(faq);
        faq = new FAQ(7, getResources().getString(R.string.faq_7));
        arrayList.add(faq);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItem(int id, int position) {
        FAQ faq = arrayList.get(position);
        Toast.makeText(getApplicationContext(), "" + faq, Toast.LENGTH_SHORT).show();
    }
}
