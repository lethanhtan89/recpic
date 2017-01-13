package vn.com.recpic.SearchScreen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.recpic.R;
import vn.com.recpic.SearchScreen.adapter.SearchPagerAdapter;
import vn.com.recpic.SearchScreen.fragment.SearchComparePriceFragment;
import vn.com.recpic.SearchScreen.fragment.SearchFavouriteLocationFragment;
import vn.com.recpic.SearchScreen.fragment.SearchHistoryFragment;
import vn.com.recpic.SearchScreen.fragment.SearchLocationFragment;

/**
 * Created by Administrator on 1/12/2017.
 */

public class SearchLocationActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView txtToolbarSearch;
    private FloatingActionButton fab_Search;
    private int[] tabIcons = {
            R.drawable.icon_location,
            R.drawable.icon_favourite_location
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        txtToolbarSearch = (TextView) findViewById(R.id.txtToolBarSearchLocation);
        txtToolbarSearch.setText(getResources().getString(R.string.search_location));


        mTabLayout = (TabLayout) findViewById(R.id.search_location_tab);
        mViewPager = (ViewPager) findViewById(R.id.search_location_viewpager);

        if(mViewPager != null){
            setupViewPager(mViewPager);
        }

//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager){
        SearchPagerAdapter adapter = new SearchPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchLocationFragment(), "Location");
        adapter.addFragment(new SearchFavouriteLocationFragment(), "Favourite locations");
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
}
