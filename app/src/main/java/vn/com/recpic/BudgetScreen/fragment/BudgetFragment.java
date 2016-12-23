package vn.com.recpic.BudgetScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.recpic.BudgetScreen.adapter.BudgetFragmentAdapter;
import vn.com.recpic.HomeScreen.fragment.ExpenseFragment;
import vn.com.recpic.HomeScreen.fragment.IncomeFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class BudgetFragment extends Fragment {
    public static TabLayout mTabLayout;
    public static ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_layout_budget, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_budget);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_budget);

        if(mViewPager != null){
            setupViewPager(mViewPager);
        }

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager mViewPager){
        BudgetFragmentAdapter adapter = new BudgetFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new TotalBudgetFragment(), "Total budget");
        adapter.addFragment(new SubBudgetFragment(), "Sub budget");
        mViewPager.setAdapter(adapter);
    }

}
