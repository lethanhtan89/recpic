package vn.com.recpic.SettingScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import vn.com.recpic.R;

/**
 * Created by Administrator on 22/12/2016.
 */

public class SettingFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        ToggleButton mTogAuto = (ToggleButton) view.findViewById(R.id.tog_auto_save_photo);
        mTogAuto.setText("");
    }
}
