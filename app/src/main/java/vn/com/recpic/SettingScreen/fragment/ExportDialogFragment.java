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
import android.widget.TextView;

import vn.com.recpic.R;

/**
 * Created by Administrator on 1/25/2017.
 */

public class ExportDialogFragment extends DialogFragment {
    private TextView txtSendfile;
    private LinearLayout lnDismiss;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_export_file, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }

    private void init(View view){
        txtSendfile = (TextView) view.findViewById(R.id.txtSendfile);
        lnDismiss = (LinearLayout) view.findViewById(R.id.lnConfirm);

        txtSendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "recpic");
                startActivity(Intent.createChooser(intent,"Save recpic with"));
            }
        });

        lnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
