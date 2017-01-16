package vn.com.recpic.PaymentPlanScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.com.recpic.PaymentPlanScreen.dialog.PaymentPlanDialogFragment;
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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_payment_plan, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_repeat, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.repeat_action_info:
                DialogFragment fragment = new PaymentPlanDialogFragment();
                fragment.show(getFragmentManager(), fragment.getTag());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
