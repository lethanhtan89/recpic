package vn.com.recpic.HomeScreen.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 05/01/2017.
 */

public class ExpenseSlideAdapter extends PagerAdapter{
    private LayoutInflater inflater;
    private int[] layouts = new int[0];
    private Context mContext;

    public ExpenseSlideAdapter(Context mContext, int[] layouts){
        this.mContext = mContext;
        this.layouts = layouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        View view = (View) object;
        container.removeView(view);
    }
}
