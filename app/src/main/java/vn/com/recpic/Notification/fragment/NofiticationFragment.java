package vn.com.recpic.Notification.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
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
 * Created by Administrator on 1/14/2017.
 */

public class NofiticationFragment extends DialogFragment{
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.tab_info);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_info);
        if(viewPager != null){
            setupViewPager(viewPager);
        }

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager mViewPager){
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsNotificationFragment(), getResources().getString(R.string.noti_news));
        adapter.addFragment(new PendingNotificationFragment(), getResources().getString(R.string.noti_pen));
        adapter.addFragment(new RejectedNotificationFragment(), getResources().getString(R.string.noti_re));
        adapter.addFragment(new CompletedNotificationFragment(), getResources().getString(R.string.noti_com));
        adapter.addFragment(new HistoryNotificationFragment(), getResources().getString(R.string.noti_his));
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_notification, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.noti_action_close:
                getChildFragmentManager().popBackStack("tag", 1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
