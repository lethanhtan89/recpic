package vn.com.recpic.HomeScreen.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import vn.com.recpic.AssetAnalyseScreen.fragment.AssetFragment;
import vn.com.recpic.BudgetScreen.fragment.BudgetFragment;
import vn.com.recpic.HomeScreen.fragment.HomeFragment;
import vn.com.recpic.HomeScreen.fragment.LogFragment;
import vn.com.recpic.NoteScreen.fragment.NotesFragment;
import vn.com.recpic.Notification.dialog.SmsDialogFragment;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.PaymentPlanScreen.dialog.PaymentPlanDialogFragment;
import vn.com.recpic.PaymentPlanScreen.fragment.PaymentPlanFragment;
import vn.com.recpic.ProfileScreen.ProfileActivity;
import vn.com.recpic.R;
import vn.com.recpic.RepeatRecordScreen.fragment.RepeatFragment;
import vn.com.recpic.SearchScreen.activity.SearchActivity;
import vn.com.recpic.SettingScreen.fragment.SettingFragment;
import vn.com.recpic.Database.MyFunctions;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int IMAGE_CAMERA = 10001;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton fab, fab_add, fab_cam, fab_sms, fab_search;
    private boolean show = false;
    private View mNavigationHeader;
    private ImageView imgProfile;
    private TextView txtName, txtEmail, txtToolbarTitle;
    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private FrameLayout mFabFrameLayout;
    private Uri uriCameraImage;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Set Navigation View Header
        mNavigationHeader = mNavigationView.getHeaderView(0);
        txtName = (TextView) mNavigationHeader.findViewById(R.id.nav_txt_name);
        txtEmail = (TextView) mNavigationHeader.findViewById(R.id.nav_txt_email);
        imgProfile = (ImageView) mNavigationHeader.findViewById(R.id.nav_imageView);
        txtToolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        mDrawerLayout.setScrimColor(R.color.white);
        toggle.syncState();

        if(mNavigationView != null){
            setupDrawerContent(mNavigationView);
        }
        mFabFrameLayout = (FrameLayout) findViewById(R.id.fr_fab);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_cam = (FloatingActionButton) findViewById(R.id.fab_camera);
        fab_sms = (FloatingActionButton) findViewById(R.id.fab_send_sms);
        fab_search = (FloatingActionButton) findViewById(R.id.fab_search);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();
        mNavigationView.setNavigationItemSelectedListener(this);
        loadHeader();
        actionFloating();
        hideFloatingButton();
    }

    private void setupDrawerContent(NavigationView mNavigationView){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void loadHeader(){
        MyFunctions myFunctions= new MyFunctions(getApplicationContext());

        txtName.setText("Tan Le");
        txtEmail.setText(myFunctions.getEmail());
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.activity_open_translate, R.animator.zoom_in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (id == R.id.main_action_list) {
            mFragmentTransaction.replace(R.id.containerView, new LogFragment()).commit();
            return true;
        }

        if(id ==R.id.main_action_noti){
            mFragmentTransaction.replace(R.id.containerView, new NofiticationFragment()).addToBackStack("tag").commit();
            txtToolbarTitle.setText(getResources().getString(R.string.ac_noti));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displayView(id);
        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;

        switch (viewId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_home));
                break;
            case R.id.nav_budget:
                fragment = new BudgetFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_budget));
                break;
            case R.id.nav_asset:
                fragment = new AssetFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_asset));
                fab.hide();
                break;
            case R.id.nav_note:
                fragment = new NotesFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_note));
                //toolbar.setG
                break;
            case R.id.nav_repeat:
                fragment = new RepeatFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_repeat));
                break;
            case R.id.nav_payment:
                fragment = new PaymentPlanFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_payment));
                break;
            case R.id.nav_setting:
                fragment = new SettingFragment();
                txtToolbarTitle.setText(getResources().getString(R.string.ac_setting));
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.containerView, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void showFloatingButton(){
        fab_add.show();
        fab_cam.show();
        fab_sms.show();
        fab_search.show();
    }

    private void hideFloatingButton(){
        fab_add.hide();
        fab_cam.hide();
        fab_sms.hide();
        fab_search.hide();
    }

    private void actionFloating(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show == false){
                    showFloatingButton();
                    show = true;
                    //mFabFrameLayout.setBackground(getResources().getDrawable(R.drawable.fab_shadow));
                    fab.setImageResource(R.drawable.icon_close);
                }else {
                    hideFloatingButton();
                    show = false;
                    fab.setImageResource(R.drawable.icon_plus_1);
                }
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(), AddExpenseActivity.class);
             startActivity(intent);
            }
        });

        fab_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String url = "tmp_" + String.valueOf(System.currentTimeMillis());
                uriCameraImage = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriCameraImage);
                startActivityForResult(intent, IMAGE_CAMERA);
            }
        });

        fab_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new SmsDialogFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });


        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        DialogFragment fragment = new PaymentPlanDialogFragment();
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }
}
