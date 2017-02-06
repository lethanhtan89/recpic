package vn.com.recpic.SettingScreen.fragment;

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
 * Created by Administrator on 2/6/2017.
 */

public class SMSDialogFragment extends DialogFragment {
    private LinearLayout lnReport, lnCancel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_sms_data, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }

    private void init(View view){
        lnReport = (LinearLayout) view.findViewById(R.id.lnConfirm);
        lnCancel = (LinearLayout) view.findViewById(R.id.lnCancel);

        lnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        lnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SendReportActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
