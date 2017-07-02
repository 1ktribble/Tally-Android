package org.tallythevote;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final static String sKEY_ACTIONBAR_TITLE = "actionBarTitle";
    private static final String Login_Key = "LOGIN_KEY";

    FirebaseUser mUser;

    private static final int sDELAY_MILLIS = 250;

    private NavigationView mNavigationView;
    private Context mContext;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;

    TextView mUserAliasTextView, mEmailAdressTextView;

    private SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tallythevote.R.layout.activity_main);
        init(savedInstanceState);
    }

    private void init(@Nullable final Bundle savedInstanceState) {
        mPref = getSharedPreferences(Login_Key, MODE_PRIVATE);

        checkAuthentication();
        bindResources();
        initializeToolbar();
        pushFragment(new PoliticianFragment());
        setupDrawer();
        reloadState(savedInstanceState);
    }

    private void checkAuthentication() {
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if(mUser != null){
                    return;
                }
                else{
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                }
            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    private void bindResources() {
        // Initialize "global" variables.
        mContext = this;
        mToolbar = (Toolbar) findViewById(org.tallythevote.R.id.mainToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_content);
        mNavigationView = (NavigationView) findViewById(R.id.slideDrawer);
        mFrameLayout = (FrameLayout) findViewById(org.tallythevote.R.id.rootLayout);
        View header = mNavigationView.getHeaderView(0);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        // Text Resources
        mUserAliasTextView = (TextView) header.findViewById(R.id.user_account_alias);
        mEmailAdressTextView = (TextView) header.findViewById(R.id.user_email_address_alias);
    }

    private void setupDrawer() {

        // get UserName from Bundle
        mUserAliasTextView.setText("Tally");
        mEmailAdressTextView.setText(mUser.getEmail());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer
        );
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            // close app
            finish();
            super.onBackPressed();
        }
    }

    private void reloadState(Bundle savedInstanceState) {
        if(savedInstanceState == null)
            pushFragment(new PoliticianFragment());
        else
            setToolbarTitle((String) savedInstanceState.getCharSequence(sKEY_ACTIONBAR_TITLE));
    }

    private void initializeToolbar() {
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_add_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()) {
            // TODO: configure buttons on bottom drawer
            case R.id.nav_politicians:
                mFrameLayout.removeAllViewsInLayout();
                pushFragment(new PoliticianFragment());
                break;
            case R.id.nav_events:
                mFrameLayout.removeAllViewsInLayout();
                pushFragment(new CurrentEventsFragment());
                break;
            case R.id.nav_feed:
                mFrameLayout.removeAllViewsInLayout();
                break;
            case R.id.nav_explore:
                mFrameLayout.removeAllViewsInLayout();
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_settings:
                mFrameLayout.removeAllViewsInLayout();
                pushFragment(new SettingsFragment());
                break;
            default:
                pushFragment(new PoliticianFragment());
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(org.tallythevote.R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    protected void onSaveInstanceState(final Bundle outState)
    {
        outState.putCharSequence(sKEY_ACTIONBAR_TITLE, getToolbarTitle());
        super.onSaveInstanceState(outState);
    }

    private CharSequence getToolbarTitle() {
        if(getSupportActionBar() != null)
            return getSupportActionBar().getTitle();

        return getString(R.string.app_name);
    }

    public void setToolbarTitle(final String titleString) {
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(titleString);
        }
    }
}
