package com.example.gradlesynctest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private TabAccessorAdapter tabAccessorAdapter;
    public int currentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager(), 0);
        myViewPager.setAdapter(tabAccessorAdapter);

        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        tabLayout.setupWithViewPager(myViewPager);

        toolbar = (Toolbar) findViewById(R.id.mainActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
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

}