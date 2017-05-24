package org.tallythevote;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    RecyclerView mRecyclerView;
    TextView tempText, zipText;
    List<Event> mEventList;
    EventItemAdapter mEventAdapter;
    FloatingActionButton floatingActionButton;
    SharedPreferences settings;
    String addressLink = "http://maps.google.com/?q=";


    public static final String USER_PREF = "User Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tallythevote.R.layout.activity_main);

        initializeRecyclerView(this);
        initializeBottomNavigationView();
        initializeToolbar();

        tempText = (TextView) findViewById(org.tallythevote.R.id.tempTextView);
        zipText = (TextView) findViewById(org.tallythevote.R.id.zipCodeText);
        floatingActionButton = (FloatingActionButton) findViewById(org.tallythevote.R.id.searchFAB);

        loadUserData();

        pushFragment(new PoliticianFragment());
    }

    private void loadUserData() {
        settings = getSharedPreferences(USER_PREF, 0);
        int zipCode = settings.getInt("userZip", 00000);
        if(zipCode == 00000){
    //        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
    //        startActivity(intent);
        }
    }

    private void initializeToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(org.tallythevote.R.id.mainToolbar);
        setSupportActionBar(myToolbar);
    }

    private void initializeBottomNavigationView() {
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(org.tallythevote.R.id.bottom_navigation_menu);

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            // TODO: configure buttons on bottom drawer
                            case org.tallythevote.R.id.home_bottom_nav:
                                mRecyclerView.setVisibility(View.GONE);
                                tempText.setVisibility(View.GONE);
                                floatingActionButton.setVisibility(View.GONE);
                                pushFragment(new PoliticianFragment());
                                break;
                            case org.tallythevote.R.id.events_bottom_nav:
                                FrameLayout frameLayout = (FrameLayout) findViewById(org.tallythevote.R.id.rootLayout);
                                frameLayout.removeAllViewsInLayout();
                                frameLayout.addView(mRecyclerView);
                                mRecyclerView.setVisibility(View.VISIBLE);
                                floatingActionButton.setVisibility(View.GONE);
                                tempText.setVisibility(View.GONE);
                                break;
                            case org.tallythevote.R.id.feed_bottom_nav:
                                frameLayout = (FrameLayout) findViewById(org.tallythevote.R.id.rootLayout);
                                frameLayout.removeAllViewsInLayout();
                                frameLayout.addView(tempText);
                                mRecyclerView.setVisibility(View.GONE);
                                floatingActionButton.setVisibility(View.GONE);
                                tempText.setVisibility(View.VISIBLE);
                                break;
                            case org.tallythevote.R.id.explore_bottom_nav:
                                frameLayout = (FrameLayout) findViewById(org.tallythevote.R.id.rootLayout);
                                frameLayout.removeAllViewsInLayout();
                                frameLayout.addView(tempText);
                                mRecyclerView.setVisibility(View.GONE);
                                floatingActionButton.setVisibility(View.GONE);
                                tempText.setVisibility(View.VISIBLE);
                                break;
                            default:
                                mRecyclerView.setVisibility(View.GONE);
                                tempText.setVisibility(View.GONE);
                                floatingActionButton.setVisibility(View.GONE);
                                pushFragment(new PoliticianFragment());
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
                ft.replace(org.tallythevote.R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }

    private void initializeRecyclerView(Context context) {
        mRecyclerView = (RecyclerView) findViewById(org.tallythevote.R.id.eventList);

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 9);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 26);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2017);
        calendar2.set(Calendar.MONTH, 3);
        calendar2.set(Calendar.DATE, 15);

        mEventList.add(new Event(00001, 74055,
                "Special Primary Election \nHouse District #75",
                "Early voting occurs Thursday, May 4 and Friday May 5 from 8 a.m. to 6 p.m.",
                calendar,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));

        mEventList.add(new Event(00002, 74055,
                "Annual School Runoff",
                "Election has completed",
                calendar2,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));

        mEventList.add(new Event(00003, 74055,
                "Meet your rep!",
                "Meet Rep. Tom Cole serving the Nth district of Oklahoma",
                calendar1,
                "<a href='"+addressLink+"111 N Main St, Owasso, OK 74055'>Owasso Town Hall</a>"));

        mEventList.add(new Event(0004, 74055,
                "Special General Election \nHouse District #75",
                "Register Now!",
                calendar,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        zipText.setText(String.valueOf(settings.getInt("userZip", 00000)));
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case org.tallythevote.R.id.settings_toolbar:
                Intent intent  = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayOptions(0, android.support.v7.app.ActionBar.DISPLAY_SHOW_TITLE);
        zipText.setText(String.valueOf(settings.getInt("userZip", 00000)));
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(org.tallythevote.R.menu.utility_bar_icons, menu);



        return true;
    }

}
