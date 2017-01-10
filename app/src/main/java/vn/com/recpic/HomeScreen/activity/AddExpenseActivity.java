package vn.com.recpic.HomeScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.com.recpic.HomeScreen.adapter.HomeFragmentAdapter;
import vn.com.recpic.HomeScreen.fragment.AddExpenseFragment;
import vn.com.recpic.HomeScreen.fragment.AddIncomeFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 06/01/2017.
 */

public class AddExpenseActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        setActionBar();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.expense_toolbar);
//        setSupportActionBar(toolbar);
        init();
    }

    private void init(){
        mTabLayout = (TabLayout) findViewById(R.id.expense_tab);
        mViewPager = (ViewPager) findViewById(R.id.expense_viewpager);

        if(mViewPager != null) {
            setupViewPager(mViewPager);
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //mTabLayout.setTabTextColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.bg_screen2));\
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddExpenseFragment(), "Expense");
        adapter.addFragment(new AddIncomeFragment(), "Income");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.hide();
        //actionBar.setDisplayHomeAsUpEnabled(true);
       // actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(getResources().getString(R.string.type_in));
        //ctionBar.setHomeAsUpIndicator(R.drawable.icon_close);
    }
}
