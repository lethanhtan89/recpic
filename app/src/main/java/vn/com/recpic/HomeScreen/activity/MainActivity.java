package vn.com.recpic.HomeScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.recpic.AssetAnalyseScreen.fragment.AssetFragment;
import vn.com.recpic.BudgetScreen.fragment.BudgetFragment;
import vn.com.recpic.BudgetScreen.fragment.SubBudgetFragment;
import vn.com.recpic.BudgetScreen.fragment.TotalBudgetFragment;
import vn.com.recpic.HomeScreen.fragment.IncomeFragment;
import vn.com.recpic.HomeScreen.fragment.HomeFragment;
import vn.com.recpic.NoteScreen.fragment.NotesFragment;
import vn.com.recpic.PaymentPlanScreen.fragment.PaymentPlanFragment;
import vn.com.recpic.ProfileScreen.ProfileActivity;
import vn.com.recpic.R;
import vn.com.recpic.RepeatRecordScreen.fragment.RepeatFragment;
import vn.com.recpic.SettingScreen.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_BUDGET = "budget";
    private static final String TAG_ASSET = "asset";
    private static final String TAG_NOTE = "note";
    private static final String TAG_REPEAT = "repeat";
    private static final String TAG_PAYMENT = "payment";
    private static final String TAG_SETTING = "setting";

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton fab, fab_edit, fab_photo, fab_copy, fab_search;
    private boolean show = false;
    private View mNavigationHeader;
    private ImageView imgProfile;
    private TextView txtName, txtEmail, txtToolbarTitle;
    private Toolbar toolbar;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_edit = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab_photo = (FloatingActionButton) findViewById(R.id.fab_cam);
        fab_copy = (FloatingActionButton) findViewById(R.id.fab_copy);
        fab_search = (FloatingActionButton) findViewById(R.id.fab_search);

        //Set Navigation View Header
        mNavigationHeader = mNavigationView.getHeaderView(0);
        txtName = (TextView) mNavigationHeader.findViewById(R.id.nav_txt_name);
        txtEmail = (TextView) mNavigationHeader.findViewById(R.id.nav_txt_email);
        imgProfile = (ImageView) mNavigationHeader.findViewById(R.id.nav_imageView);
        txtToolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        if(mNavigationView != null){
            setupDrawerContent(mNavigationView);
        }

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();
        //toolbar.setTitle(getResources().getString(R.string.ac_home));

        clickFloating();
        mNavigationView.setNavigationItemSelectedListener(this);
        loadHeader();
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
        txtName.setText("Le Thanh Tan");
        txtEmail.setText("lethanhtan89@gmail.com");
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_action_list) {
            Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id ==R.id.main_action_noti){
            Toast.makeText(this, "Noti", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
        fab_edit.show();
        fab_photo.show();
        fab_search.show();
        fab_copy.show();
    }

    private void hideFloatingButton(){
        fab_edit.hide();
        fab_photo.hide();
        fab_search.hide();
        fab_copy.hide();
    }

    private void clickFloating(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show == false){
                    showFloatingButton();
                    show = true;
                    fab.setImageResource(R.drawable.icon_close);
                }else {
                    hideFloatingButton();
                    show = false;
                    fab.setImageResource(R.drawable.icon_plus_1);
                }
            }
        });

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        fab_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
            }
        });

        fab_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Photo", Toast.LENGTH_SHORT).show();
            }
        });

        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
