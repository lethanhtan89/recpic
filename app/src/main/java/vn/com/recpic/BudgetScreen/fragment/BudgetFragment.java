package vn.com.recpic.BudgetScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.com.recpic.BudgetScreen.adapter.BudgetFragmentAdapter;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_layout_budget, null);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.budget_action_noti:
                FragmentManager mFragmentManager = getFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, new NofiticationFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
