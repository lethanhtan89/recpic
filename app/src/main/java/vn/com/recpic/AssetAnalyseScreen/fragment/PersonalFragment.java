package vn.com.recpic.AssetAnalyseScreen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.recpic.R;

/**
 * Created by Administrator on 21/12/2016.
 */

public class PersonalFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_setting, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        TextView mTxtNotePersonal = (TextView) view.findViewById(R.id.txt_note_per);
        TextView mTxtNoteAnalysis =  (TextView) view.findViewById(R.id.txt_note_analysis);

        mTxtNotePersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new PersonalHelpDialogFragment();
                fragment.show(getChildFragmentManager(), fragment.getTag());
            }
        });

        mTxtNoteAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new AnalysisHelpDialogFragment();
                fragment.show(getChildFragmentManager(), fragment.getTag());
            }
        });
    }
}
