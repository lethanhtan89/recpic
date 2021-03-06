package vn.com.recpic.SettingScreen.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vn.com.recpic.LoginScreen.activity.LoginActivity;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.ProfileScreen.ProfileActivity;
import vn.com.recpic.R;
import vn.com.recpic.Database.MyFunctions;
import vn.com.recpic.SettingScreen.activity.AboutActivity;
import vn.com.recpic.SettingScreen.activity.CardSettingActivity;
import vn.com.recpic.SettingScreen.activity.FAQActivity;
import vn.com.recpic.SettingScreen.activity.NotificationSettingActivity;

/**
 * Created by Administrator on 22/12/2016.
 */

public class SettingFragment extends Fragment {
    private Spinner spinnerDate, spinnerLanguage;
    private TextView txtProfile;
    private LinearLayout lnExport, lnSendEmail, lnCardSetting, lnNotification, lnFaq, lnAbout, lnInvite;
    private ShareActionProvider shareActionProvider;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        ToggleButton mTogAuto = (ToggleButton) view.findViewById(R.id.tog_auto_save_photo);
        mTogAuto.setText("");
        spinnerDate = (Spinner) view.findViewById(R.id.spiStartingDate);
        spinnerLanguage = (Spinner) view.findViewById(R.id.spiLanguage);
        txtProfile = (TextView) view.findViewById(R.id.txtProfile);
        lnExport = (LinearLayout) view.findViewById(R.id.lnExport);
        lnSendEmail = (LinearLayout) view.findViewById(R.id.lnSendEmail);
        lnCardSetting = (LinearLayout) view.findViewById(R.id.lnCardSetting);
        lnNotification = (LinearLayout) view.findViewById(R.id.lnNotification);
        lnFaq = (LinearLayout) view.findViewById(R.id.lnFaq);
        lnAbout = (LinearLayout) view.findViewById(R.id.lnAbout);
        lnInvite = (LinearLayout) view.findViewById(R.id.lnInvite);

        txtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        lnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new ExportDialogFragment();
                fragment.show(getFragmentManager(), fragment.getTag());
            }
        });

        lnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new SendEmailDialogFragment();
                fragment.show(getFragmentManager(), fragment.getTag());
            }
        });

        lnCardSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CardSettingActivity.class);
                startActivity(intent);
            }
        });

        lnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationSettingActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        lnFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FAQActivity.class);
                startActivity(intent);
            }
        });

        lnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        lnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                //Uri screenshotUri = Uri.parse("share");
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, "recpic");
                startActivity(Intent.createChooser(sharingIntent, "Share recpic using"));
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.starting_date, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerDate.setAdapter(arrayAdapter);

        ArrayAdapter<CharSequence> arrayAdapterLanguage = ArrayAdapter.createFromResource(getContext(), R.array.language, android.R.layout.simple_spinner_item);
        arrayAdapterLanguage.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerLanguage.setAdapter(arrayAdapterLanguage);

        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView txtSignout = (TextView) view.findViewById(R.id.profile_sign_out);
        txtSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFunctions myFunctions= new MyFunctions(getContext());
                myFunctions.logOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.budget_action_noti:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerView, new NofiticationFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setShareIntent(Intent shareIntent){
        if(shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "http://monkeylivetv.com");
        return shareIntent;
    }
}
