package vn.com.recpic.HomeScreen.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import vn.com.recpic.HomeScreen.activity.MainActivity;
import vn.com.recpic.HomeScreen.adapter.ExpenseSlideAdapter;
import vn.com.recpic.HomeScreen.model.PrefManager;
import vn.com.recpic.R;

/**
 * Created by Administrator on 05/01/2017.
 */

public class AddExpenseFragment extends BottomSheetDialogFragment {
    private ViewPager mViewPager;
    private ExpenseSlideAdapter adapter;
    private LinearLayout mDotLayout;
    private TextView[] dots;
    private PrefManager mPrefManager;
    private int[] layouts;
    private FloatingActionButton mDetailSetings;
    private CoordinatorLayout mCoordinatorLayout;
    private BottomSheetBehavior mSheetBehavior;
    private View mNestExpense;
    private NestedScrollView mNestedScrollView;
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
       // mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coor_expense_content);
        mViewPager = (ViewPager) view.findViewById(R.id.add_expense_view_pager);
        mDotLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        mDetailSetings = (FloatingActionButton) view.findViewById(R.id.bt_detail_setting);
        //LinearLayout lnMemo = (LinearLayout) view.findViewById(R.id.ln_memo);
       // mNestedScrollView = (NestedScrollView) getActivity().findViewById(R.id.nested_expense);
       // BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        //mNestExpense = getActivity().findViewById(R.id.nested_expense);

        //mSheetBehavior = BottomSheetBehavior.from(mNestedScrollView);

        mDetailSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment fragment = new ExpenseBottomSheetDialogFragment();
                fragment.show(getChildFragmentManager(), fragment.getTag());
                //               mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                if(behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
//                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                } else {
//                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }
            }
        });
        layouts = new int[]{
                R.layout.tab_add_expense_one,
                R.layout.tab_add_expense_two,
                R.layout.tab_add_expense_three,
                R.layout.tab_add_expense_four
        };

//        TextView textView = (TextView) view.findViewById(R.id.editText);
//        textView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.icon_home, 0);

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
            dots[i].setTextColor(getResources().getColor(R.color.note_title));
            mDotLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorAccent));
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

    private void lauchHomeScreen(){
        mPrefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
}
