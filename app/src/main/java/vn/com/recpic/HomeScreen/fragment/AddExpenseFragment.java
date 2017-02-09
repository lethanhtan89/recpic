package vn.com.recpic.HomeScreen.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import java.util.Calendar;

import vn.com.recpic.HomeScreen.adapter.ExpenseSlideAdapter;
import vn.com.recpic.HomeScreen.dialog.DatePickerDialogFragment;
import vn.com.recpic.HomeScreen.dialog.TimePickerDialogFragment;
import vn.com.recpic.HomeScreen.model.PrefManager;
import vn.com.recpic.R;

/**
 * Created by Administrator on 05/01/2017.
 */

public class AddExpenseFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    private ViewPager mViewPager;
    private ExpenseSlideAdapter adapter;
    private LinearLayout mDotLayout;
    private TextView[] dots;
    private PrefManager mPrefManager;
    private int[] layouts;
    private FloatingActionButton mDetailSetings;
    private TextView txtDate, txtTime;
    String date;

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
        mViewPager = (ViewPager) view.findViewById(R.id.add_expense_view_pager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        mDetailSetings = (FloatingActionButton) view.findViewById(R.id.bt_detail_setting);
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtTime = (TextView) view.findViewById(R.id.txtTime);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dialog = new DatePickerDialog(
//                        getActivity(),this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
////                dialog.setThemeDark(true);
////                dialog.vibrate(true);
////                dialog.dismissOnPause(true);
////                dialog.showYearPickerFirst(false);
////                dialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
//                dialog.setTitle("Choose a date, please");
//                dialog.show(getFragmentManager(), "Datepickerdialog");
                DialogFragment fragment = new DatePickerDialogFragment(date);
                fragment.show(getFragmentManager(), fragment.getTag());
                txtDate.setText(date);
            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                TimePickerDialog timepickerdialog = TimePickerDialog.newInstance((TimePickerDialog.OnTimeSetListener) getContext(),
//                        now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
//                timepickerdialog.setThemeDark(false); //Dark Theme?
//                timepickerdialog.vibrate(false); //vibrate on choosing time?
//                timepickerdialog.dismissOnPause(false); //dismiss the dialog onPause() called?
//                timepickerdialog.enableSeconds(true); //show seconds?
//
//                //Handling cancel event
//                timepickerdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
//                        Toast.makeText(getContext(), "Cancel choosing time", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                timepickerdialog.show(getActivity().getFragmentManager(), "Timepickerdialog");
                DialogFragment fragment = new TimePickerDialogFragment();
                fragment.show(getFragmentManager(), fragment.getTag());
            }
        });


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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

//    @Override
//    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//        String date = "You picked: " + dayOfMonth + "/" + monthOfYear + "/" + year;
//        txtDate.setText(date);
//    }
//
//    @Override
//    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
//        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
//        String minuteString = minute < 10 ? "0" + minute : "" + minute;
//        String secondString = second < 10 ? "0" + second : "" + second;
//        String time = "You picked the following time: " + hourString + "h" + minuteString + "m" + secondString + "s";
//        txtTime.setText(time);
//    }
}
