package vn.com.recpic.HomeScreen.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.recpic.HomeScreen.adapter.ExpenseSlideAdapter;
import vn.com.recpic.HomeScreen.model.PrefManager;
import vn.com.recpic.R;

/**
 * Created by Administrator on 05/01/2017.
 */

public class AddIncomeFragment extends Fragment {
    private ViewPager mViewPager;
    private ExpenseSlideAdapter adapter;
    private LinearLayout mDotLayout;
    private TextView[] dots;
    private PrefManager mPrefManager;
    private int[] layouts;
    private FloatingActionButton mDetailSetings;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefManager = new PrefManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        mViewPager = (ViewPager) view.findViewById(R.id.add_income_view_pager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.income_layoutDots);

        layouts = new int[]{
                R.layout.tab_add_income_one,
                R.layout.tab_add_income_two,
        };

        addBottomDots(0);
        changeStatusBarColor();
        adapter = new ExpenseSlideAdapter(getContext(), layouts);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(listener);

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
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

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
}
