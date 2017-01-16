package vn.com.recpic.HomeScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.com.recpic.HomeScreen.adapter.HomeFragmentAdapter;
import vn.com.recpic.R;

/**
 * Created by Administrator on 05/01/2017.
 */

public class AddHomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_layout_home, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_home);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_home);
        if(mViewPager != null){
            setupViewPager(mViewPager);
        }

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new AddExpenseFragment(), "Expense");
        adapter.addFragment(new AddIncomeFragment(), "Income");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add_action_cancel:
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
