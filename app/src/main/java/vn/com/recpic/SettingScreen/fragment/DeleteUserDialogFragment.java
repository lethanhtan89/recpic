package vn.com.recpic.SettingScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import vn.com.recpic.R;

/**
 * Created by Administrator on 2/8/2017.
 */

public class DeleteUserDialogFragment extends DialogFragment {
    private EditText etContent;
    private LinearLayout lnDis, lnConfirm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_del_user, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }

    private void init(View view){
        etContent = (EditText) view.findViewById(R.id.et_content);
        lnConfirm =(LinearLayout) view.findViewById(R.id.lnConfirm);
        lnDis = (LinearLayout) view.findViewById(R.id.lnDissmis);

        lnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
