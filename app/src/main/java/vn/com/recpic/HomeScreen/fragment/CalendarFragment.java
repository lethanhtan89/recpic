package vn.com.recpic.HomeScreen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 1/16/2017.
 */

public class CalendarFragment extends Fragment {
    private Context mContext;
    private FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public CalendarFragment(){}

    public static CalendarFragment newInstance(Context mContext){
        CalendarFragment fragment = new CalendarFragment();
        fragment.mContext = mContext;
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        setHasOptionsMenu(true);
        init(view);
        return view;
    }

    private void init(View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.calendar_toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle(" ");
        TextView txtCalendarTitle = (TextView) view.findViewById(R.id.txtToolBarCalendar);
        txtCalendarTitle.setText(view.getResources().getString(R.string.calendar));
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
                fragmentManager = getFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerView, new NofiticationFragment()).commit();
                break;
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
