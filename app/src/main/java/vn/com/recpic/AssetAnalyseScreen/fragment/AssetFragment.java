package vn.com.recpic.AssetAnalyseScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.recpic.AssetAnalyseScreen.adapter.AssetFragmentAdapter;
import vn.com.recpic.BudgetScreen.adapter.BudgetFragmentAdapter;
import vn.com.recpic.BudgetScreen.fragment.SubBudgetFragment;
import vn.com.recpic.BudgetScreen.fragment.TotalBudgetFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class AssetFragment extends Fragment {
    public static TabLayout mTabLayout;
    public static ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_layout_asset, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_asset);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_asset);

        if(mViewPager != null){
            setupViewPager(mViewPager);
        }

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager mViewPager){
        AssetFragmentAdapter adapter = new AssetFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new PersonalFragment(), "Personal settings");
        adapter.addFragment(new AnalysisFragment(), "Analysis");
        mViewPager.setAdapter(adapter);
    }

}
