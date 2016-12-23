package vn.com.recpic.BudgetScreen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 21/12/2016.
 */

public class BudgetFragmentAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> mFragment = new ArrayList<>();
    private final ArrayList<String> mFragmentTitle = new ArrayList<>();

    public BudgetFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        mFragment.add(fragment);
        mFragmentTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }
}
