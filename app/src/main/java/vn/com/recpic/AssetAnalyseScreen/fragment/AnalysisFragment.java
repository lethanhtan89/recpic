package vn.com.recpic.AssetAnalyseScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.recpic.HomeScreen.adapter.HomeFragmentAdapter;
import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class AnalysisFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_home);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_home);
        if(mViewPager != null){
            setupViewPager(mViewPager);
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new MyAnalysisFragment(), "My");
        adapter.addFragment(new OthersAnalysisFragment(), "Others");
        viewPager.setAdapter(adapter);
    }
}
