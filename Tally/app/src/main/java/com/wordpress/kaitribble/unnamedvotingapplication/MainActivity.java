package com.wordpress.kaitribble.unnamedvotingapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    RecyclerView mRecyclerView;
    List<Event> mEventList;
    EventItemAdapter mEventAdapter;
    public static final String USER_PREF = "User Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView(this);
        initializeBottomNavigationView();
        initializeToolbar();
        
        loadUserData();

    }

    private void loadUserData() {
        SharedPreferences settings = getSharedPreferences(USER_PREF, 0);
        int zipCode = settings.getInt("userZip", 00000);
        if(zipCode == 00000){
            Intent intent = new Intent(MainActivity.this, EnterZipActivity.class);
            startActivity(intent);
        }
    }

    private void initializeToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar);
    }

    private void initializeBottomNavigationView() {
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_menu);

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            // TODO: configure buttons on bottom drawer
                            case R.id.home_bottom_nav:
                                mRecyclerView.setVisibility(View.GONE);
                                pushFragment(new PoliticianFragment());
                                break;
                            case R.id.events_bottom_nav:
                                mRecyclerView.setVisibility(View.VISIBLE);
                                break;
                            case R.id.alerts_bottom_nav:
                                mRecyclerView.setVisibility(View.GONE);
                                break;
                            case R.id.feed_bottom_nav:
                                mRecyclerView.setVisibility(View.GONE);
                                break;
                            case R.id.explore_bottom_nav:
                                mRecyclerView.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                }
        );
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }


    private void initializeRecyclerView(Context context) {
        mRecyclerView = (RecyclerView) findViewById(R.id.eventList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        initializeTestData();
        initializeAdapter();
    }

    private void initializeAdapter() {
        mEventAdapter = new EventItemAdapter( mEventList);
        mRecyclerView.setAdapter(mEventAdapter);
    }

    private void initializeTestData() {
        mEventList = new ArrayList<>();
        mEventList.add(new Event(00001, 74055,
                "Special Primary Election \nHouse District #75",
                "Early voting occurs Thursday, May 4 and Friday May 5 from 8 a.m. to 6 p.m."));

        mEventList.add(new Event(00002, 74055,
                "Annual School Runoff",
                "Election has completed"));

        mEventList.add(new Event(00003, 74055,
                "Meet your rep!",
                "Meet Rep. Tom Cole serving the Nth district of Oklahoma"));

        mEventList.add(new Event(0004, 74055,
                "Special General Election \nHouse District #75",
                "Register Now!"));
    }


    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_toolbar:
                Intent intent  = new Intent(MainActivity.this, EnterZipActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.utility_bar_icons, menu);
        return true;
    }

}
