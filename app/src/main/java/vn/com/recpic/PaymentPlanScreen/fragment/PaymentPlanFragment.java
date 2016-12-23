package vn.com.recpic.PaymentPlanScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.recpic.R;

/**
 * Created by Administrator on 17/12/2016.
 */

public class PaymentPlanFragment extends Fragment {

    public PaymentPlanFragment(){

    }

    public static PaymentPlanFragment newInstance(){
        PaymentPlanFragment fragment = new PaymentPlanFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_plan, container, false);
        return view;
    }
}
