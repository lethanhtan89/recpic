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

import org.w3c.dom.Text;

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
    private TextView txtToolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        init();
        setupToolbar();
    }

    private void init(){
        mTabLayout = (TabLayout) findViewById(R.id.expense_tab);
        mViewPager = (ViewPager) findViewById(R.id.expense_viewpager);
        txtToolbarTitle = (TextView) findViewById(R.id.txtToolBarAddExpense);

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
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.expense_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.icon_cancel));
        txtToolbarTitle.setText(getResources().getString(R.string.type_in));
    }
}
