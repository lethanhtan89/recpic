package vn.com.recpic.SearchScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.recpic.R;
import vn.com.recpic.SearchScreen.adapter.SearchPagerAdapter;
import vn.com.recpic.SearchScreen.fragment.SearchComparePriceFragment;
import vn.com.recpic.SearchScreen.fragment.SearchHistoryFragment;

/**
 * Created by Administrator on 1/12/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView txtToolbarSearch;
    private FloatingActionButton fab_Search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        txtToolbarSearch = (TextView) findViewById(R.id.txtToolBarSearch);
        txtToolbarSearch.setText("< 2017 / 01 >");
        fab_Search = (FloatingActionButton) findViewById(R.id.fab_scroll_search);
        fab_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.search_tab);
        mViewPager = (ViewPager) findViewById(R.id.search_viewpager);

        if(mViewPager != null){
            setupViewPager(mViewPager);
        }

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SearchPagerAdapter adapter = new SearchPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchHistoryFragment(), "My History");
        adapter.addFragment(new SearchComparePriceFragment(), "Prepare price");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
            case R.id.search_bookmark:
                Toast.makeText(getApplicationContext(), "Search bookmark", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
