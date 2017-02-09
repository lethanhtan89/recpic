package vn.com.recpic.HomeScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import vn.com.recpic.HomeScreen.adapter.HomeFragmentAdapter;
import vn.com.recpic.HomeScreen.dialog.ExpenseLogFragment;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class LogFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    Menu mMenu;

    public LogFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_layout_home, null);
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
        adapter.addFragment(new ExpenseLogFragment(), "Expense");
        adapter.addFragment(new IncomeLogFragment(), "Income");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //setupViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_log, menu);
        this.mMenu = menu;
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (id){
            case R.id.main_action_log:
                transaction.replace(R.id.containerView, new HomeFragment()).commit();
                break;
            case R.id.main_action_noti:
                transaction.replace(R.id.containerView, new NofiticationFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
