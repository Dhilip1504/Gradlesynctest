package com.example.gradlesynctest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    public Toolbar toolbar;
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private TabAccessorAdapter tabAccessorAdapter;
    private FloatingActionButton myfab;
    public int currentPage=0;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static String loginStatus = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String status = sharedPreferences.getString(loginStatus, "NO");
        if(status.equals("NO")){
            Intent intent = new Intent(MainActivity.this, Mobilelogin.class);
            startActivity(intent);
            finish();
        }

        mAuth = FirebaseAuth.getInstance();

        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager(), 0);
        myViewPager.setAdapter(tabAccessorAdapter);

        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        tabLayout.setupWithViewPager(myViewPager);

        toolbar = (Toolbar) findViewById(R.id.mainActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        myfab = (FloatingActionButton) findViewById(R.id.fab);

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                fabIconChange();
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar, menu);

        switch (currentPage){

            case 0:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(true);
                menu.findItem(R.id.starredMsg).setVisible(true);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(false);
                menu.findItem(R.id.clearLogs).setVisible(false);
                break;

            case 1:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(false);
                menu.findItem(R.id.starredMsg).setVisible(false);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(true);
                menu.findItem(R.id.clearLogs).setVisible(false);
                break;

            case 2:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(false);
                menu.findItem(R.id.starredMsg).setVisible(false);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(false);
                menu.findItem(R.id.clearLogs).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch (currentPage){

            case 0:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(true);
                menu.findItem(R.id.starredMsg).setVisible(true);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(false);
                menu.findItem(R.id.clearLogs).setVisible(false);
                break;

            case 1:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(false);
                menu.findItem(R.id.starredMsg).setVisible(false);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(true);
                menu.findItem(R.id.clearLogs).setVisible(false);
                break;

            case 2:
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.newGroup).setVisible(false);
                menu.findItem(R.id.starredMsg).setVisible(false);
                menu.findItem(R.id.settings).setVisible(true);
                menu.findItem(R.id.scheduleSettings).setVisible(false);
                menu.findItem(R.id.clearLogs).setVisible(true);
        }

        return true;
    }

    public void fabIconChange(){

        switch (currentPage){
            case 0:
                myfab.setImageResource(R.drawable.chats_fab_icon);
                break;
            case 1:
                myfab.setImageResource(R.drawable.schedules_fab_icon);
                break;
            case 2:
                myfab.setImageResource(R.drawable.calls_fab_icon);
        }

    }

}