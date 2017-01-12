package vn.com.recpic.Notification.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import vn.com.recpic.Notification.activity.SendReportActivity;
import vn.com.recpic.R;

/**
 * Created by Administrator on 1/12/2017.
 */

public class SmsDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_sms, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }

    private void init(View view){
        LinearLayout lnConfirm = (LinearLayout) view.findViewById(R.id.lnConfirm);
        LinearLayout lnCancel = (LinearLayout) view.findViewById(R.id.lnCancel);

        lnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        lnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SendReportActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
}
