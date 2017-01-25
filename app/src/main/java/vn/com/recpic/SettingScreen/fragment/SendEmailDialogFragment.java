package vn.com.recpic.SettingScreen.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import vn.com.recpic.Notification.activity.SendReportActivity;
import vn.com.recpic.R;
import vn.com.recpic.Util.Util;

/**
 * Created by Administrator on 1/17/2017.
 */

public class SendEmailDialogFragment extends DialogFragment {
    private static final String TAG = SendEmailDialogFragment.class.getSimpleName();
    private TextView txtSomeThings, txtGenaral;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_send_email, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }
    private void init(View view){
        LinearLayout lnConfirm = (LinearLayout) view.findViewById(R.id.lnConfirm);
        txtSomeThings = (TextView) view.findViewById(R.id.txt_somethings);
        txtGenaral = (TextView) view.findViewById(R.id.txt_genaral);

        txtSomeThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getContext(), SendReportActivity.class);
                startActivity(intent);
            }
        });

        txtGenaral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SendReportActivity.class);
                startActivity(intent);
            }
        });
        lnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
