package vn.com.recpic.HomeScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.recpic.HomeScreen.adapter.HomeFragmentAdapter;
import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class HomeFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_layout_home, null);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_home);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_home);

        if(viewPager != null){
            setupViewPager(viewPager);
        }

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new ExpenseFragment(), "Expense");
        adapter.addFragment(new IncomeFragment(), "Income");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //setupViewPager(viewPager);
    }
}
