package vn.com.recpic.HomeScreen.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

import vn.com.recpic.HomeScreen.fragment.CalendarFragment;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 1/16/2017.
 */

public class CalendarActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        init();
    }

    private void init(){
        transaction  = getSupportFragmentManager().beginTransaction();
        if(fragment != null){
            transaction.remove(fragment);
        }
        transaction.add(R.id.calendar_main_content, new CalendarFragment()).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.budget_action_noti){
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.containerView, new NofiticationFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
