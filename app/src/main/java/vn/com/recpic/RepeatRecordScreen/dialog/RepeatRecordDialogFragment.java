package vn.com.recpic.RepeatRecordScreen.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import vn.com.recpic.R;

/**
 * Created by Administrator on 1/16/2017.
 */

public class RepeatRecordDialogFragment extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_repeat_records, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }

    private void init(View view){
        LinearLayout lnConfirm = (LinearLayout) view.findViewById(R.id.lnConfirm);
        lnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
