package vn.com.recpic.ProfileScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.recpic.AssetAnalyseScreen.dialog.PersonalHelpDialogFragment;
import vn.com.recpic.ProfileScreen.fragment.ProfileDialogFragment;
import vn.com.recpic.R;

/**
 * Created by Administrator on 19/12/2016.
 */

public class ProfileActivity extends AppCompatActivity {
    private ImageView imgProfile;
    private LinearLayout lnconfirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_profile);
        setupToolbar();
        init();
    }

    private void init(){
        imgProfile = (ImageView) findViewById(R.id.img_profile_user);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new ProfileDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        TextView txtProfileTitle = (TextView) findViewById(R.id.txtToolBarProfile);
        txtProfileTitle.setText(getResources().getString(R.string.profile));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
