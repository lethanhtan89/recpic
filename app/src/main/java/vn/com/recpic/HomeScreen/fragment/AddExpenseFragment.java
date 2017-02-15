package vn.com.recpic.HomeScreen.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

import vn.com.recpic.HomeScreen.adapter.ExpenseSlideAdapter;
import vn.com.recpic.HomeScreen.dialog.DatePickerDialogFragment;
import vn.com.recpic.HomeScreen.model.PrefManager;
import vn.com.recpic.R;

/**
 * Created by Administrator on 05/01/2017.
 */

public class AddExpenseFragment extends DialogFragment{
    private ViewPager mViewPager;
    private ExpenseSlideAdapter adapter;
    private LinearLayout mDotLayout;
    private TextView[] dots;
    private PrefManager mPrefManager;
    private int[] layouts;
    private FloatingActionButton mDetailSetings;
    private TextView txtDate, txtTime;
    private int year, month, day, hour, minute;
    String mDate;
    public static final int DATEPICKER_FRAGMENT=1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefManager = new PrefManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        setupDateTime(view);
        mViewPager = (ViewPager) view.findViewById(R.id.add_expense_view_pager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        mDetailSetings = (FloatingActionButton) view.findViewById(R.id.bt_detail_setting);
        mDetailSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        layouts = new int[]{
                R.layout.tab_add_expense_one,
                R.layout.tab_add_expense_two,
                R.layout.tab_add_expense_three,
                R.layout.tab_add_expense_four
        };

        addBottomDots(0);
        changeStatusBarColor();
        adapter = new ExpenseSlideAdapter(getContext(), layouts);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private void setupDateTime(View view){
        final Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        final int real_month = month + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtTime = (TextView) view.findViewById(R.id.txtTime);

        String mMonth = real_month < 10 ? "0" + real_month : "" + real_month;
        final String mDay = day < 10 ? "0" + day: "" + day;
        String mHour = hour < 10 ? "0" + hour : "" + hour;
        String mMinute = minute < 10 ? "0" + minute : "" + minute;
        mDate = year + "/" + mMonth + "/" + mDay;

        txtDate.setText(mDate);

        if(hour < 12 && hour >=0) {
            txtTime.setText(mHour + ":" + mMinute + " " + getResources().getString(R.string.AM));
        }
        else {
            txtTime.setText(mHour + ":" + mMinute + " " + getResources().getString(R.string.PM));
        }

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),datePickerListener,
                        year,
                        real_month,
                        day
                );
                dialog.setTitle(getResources().getString(R.string.set_date));
                dialog.show();
//                DialogFragment fragment = new DatePickerDialogFragment(mDate);
//                fragment.show(getFragmentManager(), mDate);

            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(
                        getActivity(),
                        timePickerListener,
                        hour,
                        minute,
                        true
                );
                dialog.setTitle(getResources().getString(R.string.set_time));
                dialog.show();
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        mDotLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mDotLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.login_button));
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month += 1;
            String monthString = month < 10 ? "0" + month : "" + month;
            String dayString = dayOfMonth < 10 ? "0" + dayOfMonth: "" + dayOfMonth;
            String date = year + "/" + monthString + "/" + dayString;
            txtDate.setText(date);
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
            String minuteString = minute < 10 ? "0" + minute : "" + minute;
            String time = hourString + ":" + minuteString;
            if(hourOfDay < 12 && hourOfDay >=0) {
                txtTime.setText(time + " " + getResources().getString(R.string.AM));
            }
            else {
                txtTime.setText(time + " " + getResources().getString(R.string.PM));
            }
        }
    };


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case DATEPICKER_FRAGMENT:
//                if(resultCode == Activity.RESULT_OK){
//                    Bundle bundle=data.getExtras();
//                    String resultDate = bundle.getString("date","error");
//                    txtDate.setText(resultDate);
//                }
//        }
//    }
}
